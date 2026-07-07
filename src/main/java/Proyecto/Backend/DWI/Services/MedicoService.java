package Proyecto.Backend.DWI.Services;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto.Backend.DWI.Models.Medico;
import Proyecto.Backend.DWI.Models.Sede;
import Proyecto.Backend.DWI.Models.Servicio;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.MedicoRepository;
import Proyecto.Backend.DWI.Repositories.SedeRepository;
import Proyecto.Backend.DWI.Repositories.ServicioRepository;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;
import Proyecto.Backend.DWI.Dtos.Request.MedicoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.MedicoDTOResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final SedeRepository sedeRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository; 

    public MedicoService(MedicoRepository medicoRepository, SedeRepository sedeRepository, 
                         UsuarioRepository usuarioRepository, ServicioRepository servicioRepository) {
        this.medicoRepository = medicoRepository;
        this.sedeRepository = sedeRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
    }

    // READ: Todos los médicos (para el panel del Admin)
    public List<MedicoDTOResponse> obtenerTodos() {
        return medicoRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // READ: Filtrar médicos disponibles (para el paciente cuando hace su cita)
    public List<MedicoDTOResponse> filtrarParaCita(Long sedeId, Long servicioId) {
        return medicoRepository.filtrarParaCita(sedeId, servicioId).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // CREATE
    @Transactional
    public MedicoDTOResponse crearMedico(MedicoDTORequest request) {
        Usuario usuario = null;
        if (request.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }
        
        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        Servicio servicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado")); // Buscamos la especialidad

        Medico medico = new Medico();
        medico.setUsuarioId(usuario);
        medico.setNombre(request.getNombre());
        medico.setApellido(request.getApellido());
        medico.setFotoUrl(request.getFotoUrl());
        medico.setSedeId(sede);
        medico.setServicioId(servicio); // Asignamos la especialidad
        medico.setEstado(true); // Activo por defecto al crear

        return convertirAResponse(medicoRepository.save(medico));
    }

    // UPDATE
    @Transactional
    public MedicoDTOResponse actualizarMedico(Long id, MedicoDTORequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        
        Usuario usuario = null;
        if (request.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }
        
        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        Servicio servicio = servicioRepository.findById(request.getServicioId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        medico.setUsuarioId(usuario);
        medico.setNombre(request.getNombre());
        medico.setApellido(request.getApellido());
        medico.setFotoUrl(request.getFotoUrl());
        medico.setSedeId(sede);
        medico.setServicioId(servicio);

        return convertirAResponse(medicoRepository.save(medico));
    }

    // DELETE LÓGICO 
    @Transactional
    public void deshabilitarMedico(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        medico.setEstado(false);
        medicoRepository.save(medico);
    }

    // MAPPER
    private MedicoDTOResponse convertirAResponse(Medico medico) {
        return new MedicoDTOResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getFotoUrl(),
                medico.getSedeId().getNombre(),
                medico.getServicioId().getNombre(), // Extraemos el nombre del servicio
                medico.isEstado()
        );
    }
}