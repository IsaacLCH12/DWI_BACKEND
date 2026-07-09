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

    @Transactional
    public CitaDTOResponse crearCita(CitaDTORequest request) {
        String correoLogueado = obtenerCorreoDelToken();

        Paciente pacienteActual = pacienteRepository.buscarPorUsuarioCorreo(correoLogueado)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado en el sistema. Actualiza tu perfil primero."));
    
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

    @Transactional
    public CitaDTOResponse cambiarEstadoCita(Long id, String nuevoEstado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(nuevoEstado);
        return convertirADTOResponse(citaRepository.save(cita));
    }

    public List<CitaDTOResponse> obtenerMisCitas() {
        String correoLogueado = obtenerCorreoDelToken();
        List<Cita> citasEntity = citaRepository.buscarPorPacienteUsuarioCorreo(correoLogueado);
        return mapearListaCitas(citasEntity);
    }

    public List<CitaDTOResponse> obtenerHistorial() {
        String correoLogueado = obtenerCorreoDelToken();
        List<Cita> citasEntity = citaRepository.buscarHistorialPorPacienteCorreo(correoLogueado);
        return mapearListaCitas(citasEntity);
    }

    public List<CitaDTOResponse> obtenerTodasLasCitas() {
        List<Cita> citasEntity = citaRepository.findAll();
        return mapearListaCitas(citasEntity);
    }

    private String obtenerCorreoDelToken() {
        Object main = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) main).getUsername();
    }

    @Transactional
    public CitaDTOResponse actualizarCita(Long id, CitaDTORequest request) {
        
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + id));

        if (citaExistente.getEstado().equalsIgnoreCase("CANCELADA")) {
            throw new RuntimeException("No puedes modificar una cita que ya fue cancelada.");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin) {
            String correoLogueado = ((UserDetails) principal).getUsername();
            String correoDuenioCita = citaExistente.getPacienteId().getUsuarioId().getCorreo();
            
            if (!correoLogueado.equals(correoDuenioCita)) {
                throw new RuntimeException("Acceso Denegado: No puedes modificar la cita de otro paciente.");
            }
        }

        Medico nuevoMedico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Servicio nuevoServicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Sede nuevaSede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        
        if (!nuevaSede.getEstado()) {
             throw new RuntimeException("La nueva sede seleccionada no se encuentra disponible.");
        }

        citaExistente.setMedico(nuevoMedico);
        citaExistente.setServicioId(nuevoServicio);
        citaExistente.setSede(nuevaSede);
        citaExistente.setFechaHora(request.getFechaHora());
        
        Cita citaActualizada = citaRepository.save(citaExistente);
        return convertirADTOResponse(citaActualizada);
    }

    @Transactional
    public CitaDTOResponse cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el ID: " + id));
        
        if (!cita.getEstado().equalsIgnoreCase("PROGRAMADA")) {
            throw new RuntimeException("No se puede modificar una cita con estado: " + cita.getEstado());
        }

        cita.setEstado("CANCELADA");
        Cita citaActualizada = citaRepository.save(cita);
        return convertirADTOResponse(citaActualizada);
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
        
        // 💡 NUEVO: Guardamos el precio del servicio para que viaje a Angular
        if (cita.getServicioId() != null) {
            response.setPrecioServicio(cita.getServicioId().getPrecio());
        }
        
        return response;
    }
}