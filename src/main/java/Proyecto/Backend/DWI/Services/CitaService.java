package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Dtos.Request.CitaDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.CitaDTOResponse;
import Proyecto.Backend.DWI.Models.Cita;
import Proyecto.Backend.DWI.Models.Medico;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.Sede;
import Proyecto.Backend.DWI.Models.Servicio;
import Proyecto.Backend.DWI.Repositories.CitaRepository;
import Proyecto.Backend.DWI.Repositories.MedicoRepository;
import Proyecto.Backend.DWI.Repositories.PacienteRepository;
import Proyecto.Backend.DWI.Repositories.SedeRepository;
import Proyecto.Backend.DWI.Repositories.ServicioRepository;
import jakarta.transaction.Transactional;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ServicioRepository servicioRepository;
    private final SedeRepository sedeRepository;

    public CitaService(CitaRepository citaRepository, PacienteRepository pacienteRepository, 
                       MedicoRepository medicoRepository, ServicioRepository servicioRepository, 
                       SedeRepository sedeRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.servicioRepository = servicioRepository;
        this.sedeRepository = sedeRepository;
    }

    // CREATE: Registrar cita usando el token
    @Transactional
    public CitaDTOResponse crearCita(CitaDTORequest request) {
        String dniLogueado = obtenerDniDelToken();

        Paciente pacienteActual = pacienteRepository.buscarPorUsuarioDni(dniLogueado)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado en el sistema"));
    
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Servicio servicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        
        if (!sede.getEstado()) {
             throw new RuntimeException("La sede seleccionada no se encuentra disponible.");
        }

        Cita nuevaCita = new Cita();
        nuevaCita.setPacienteId(pacienteActual);
        nuevaCita.setMedico(medico);
        nuevaCita.setServicioId(servicio);
        nuevaCita.setSede(sede);
        nuevaCita.setFechaHora(request.getFechaHora());
        nuevaCita.setEstado("PROGRAMADA");
    
        Cita citaGuardada = citaRepository.save(nuevaCita);
        return convertirADTOResponse(citaGuardada);
    }

    // READ: Listar solo las citas del Paciente logueado
    public List<CitaDTOResponse> obtenerMisCitas() {
        String dniLogueado = obtenerDniDelToken();
        List<Cita> citasEntity = citaRepository.buscarPorPacienteUsuarioDni(dniLogueado);
        return mapearListaCitas(citasEntity);
    }

    // READ: Listar TODAS las citas 
    public List<CitaDTOResponse> obtenerTodasLasCitas() {
        List<Cita> citasEntity = citaRepository.findAll();
        return mapearListaCitas(citasEntity);
    }

    // UPDATE: Modificar/Reprogramar una Cita
    @Transactional
    public CitaDTOResponse actualizarCita(Long id, CitaDTORequest request) {
        
        // 1. Buscamos la cita que queremos modificar
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + id));

        // 2. Regla de Negocio: No se puede modificar una cita cancelada
        if (citaExistente.getEstado().equalsIgnoreCase("CANCELADA")) {
            throw new RuntimeException("No puedes modificar una cita que ya fue cancelada.");
        }

        // 3. Seguridad: Si es un paciente, verificamos que la cita le pertenezca
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            String dniLogueado = ((UserDetails) principal).getUsername();
            String dniDuenioCita = citaExistente.getPacienteId().getUsuarioId().getDni();
            
            if (!dniLogueado.equals(dniDuenioCita)) {
                throw new RuntimeException("Acceso Denegado: No puedes modificar la cita de otro paciente.");
            }
        }

        // 4. Buscamos los nuevos datos (por si cambió de médico, sede o servicio)
        Medico nuevoMedico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Servicio nuevoServicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Sede nuevaSede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        
        if (!nuevaSede.getEstado()) {
             throw new RuntimeException("La nueva sede seleccionada no se encuentra disponible.");
        }

        // 5. Actualizamos los datos de la cita existente
        citaExistente.setMedico(nuevoMedico);
        citaExistente.setServicioId(nuevoServicio);
        citaExistente.setSede(nuevaSede);
        citaExistente.setFechaHora(request.getFechaHora());
        
        // (El paciente y el estado se mantienen igual, no se cambian)

        // 6. Guardamos y devolvemos el DTO actualizado
        Cita citaActualizada = citaRepository.save(citaExistente);
        return convertirADTOResponse(citaActualizada);
    }

    // UPDATE / DELETE: Cancelación de la cita
    @Transactional
    public CitaDTOResponse cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + id));
        
        // Regla de negocio: No se puede cancelar una cita que ya fue completada o previamente cancelada
        if (!cita.getEstado().equalsIgnoreCase("PROGRAMADA")) {
            throw new RuntimeException("No se puede modificar una cita con estado: " + cita.getEstado());
        }

        cita.setEstado("CANCELADA");
        Cita citaActualizada = citaRepository.save(cita);
        return convertirADTOResponse(citaActualizada);
    }

    // Métodos Auxiliares de conversión y seguridad
    private String obtenerDniDelToken() {
        Object main = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) main).getUsername();
    }

    private List<CitaDTOResponse> mapearListaCitas(List<Cita> citasEntity) {
        List<CitaDTOResponse> listaResponse = new ArrayList<>();
        for (Cita c : citasEntity) {
            listaResponse.add(convertirADTOResponse(c));
        }
        return listaResponse;
    }
    
    private CitaDTOResponse convertirADTOResponse(Cita cita) {
        CitaDTOResponse response = new CitaDTOResponse();
        response.setId(cita.getId());
        response.setFechaHora(cita.getFechaHora());
        response.setEstadoCita(cita.getEstado());
        response.setNombrePaciente(cita.getPacienteId().getNombre() + " " + cita.getPacienteId().getApellido());
        response.setNombreServicio(cita.getServicioId().getNombre());
        response.setNombreMedico(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
        response.setNombreSede(cita.getSede().getNombre());
        return response;
    }
}
