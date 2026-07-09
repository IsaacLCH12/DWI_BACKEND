package Proyecto.Backend.DWI.Services;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import Proyecto.Backend.DWI.Dtos.Request.PacienteRequestDTO;
import Proyecto.Backend.DWI.Dtos.Response.PacienteDTOResponse;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.PacienteRepository;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;

    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PacienteDTOResponse> obtenerTodas() {
        return pacienteRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PacienteDTOResponse obtenerPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return convertirAResponse(paciente);
    }

    @Transactional
    public PacienteDTOResponse crearPaciente(PacienteRequestDTO request) {
        // Buscamos al Usuario 
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsuarioId(usuario);
        nuevoPaciente.setNombre(request.getNombre());
        nuevoPaciente.setApellido(request.getApellido());
        nuevoPaciente.setCorreo(request.getCorreo());
        nuevoPaciente.setTelefono(request.getTelefono());

        Paciente guardado = pacienteRepository.save(nuevoPaciente);
        return convertirAResponse(guardado);
    }

    @Transactional
    public PacienteDTOResponse actualizarPaciente(Long id, PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        paciente.setUsuarioId(usuario);
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setCorreo(request.getCorreo());
        paciente.setTelefono(request.getTelefono());

        return convertirAResponse(pacienteRepository.save(paciente));
    }

 
   private PacienteDTOResponse convertirAResponse(Paciente paciente) {
    PacienteDTOResponse response = new PacienteDTOResponse();
    response.setId(paciente.getId());
    response.setNombre(paciente.getNombre());
    response.setApellido(paciente.getApellido());
    response.setCorreo(paciente.getCorreo());
    response.setTelefono(paciente.getTelefono());
    response.setUsuarioId(paciente.getUsuarioId().getId());
    
    // 💡 ¡ESTA ES LA LÍNEA QUE FALTA! Saca el DNI de la relación
    if (paciente.getUsuarioId() != null) {
        response.setDni(paciente.getUsuarioId().getDni());
    }

    return response;
}
}

