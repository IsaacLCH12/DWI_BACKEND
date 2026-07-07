package Proyecto.Backend.DWI.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Proyecto.Backend.DWI.Dtos.Request.IniciarSesionDTORequest;
import Proyecto.Backend.DWI.Dtos.Request.RegistrarDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.AuthDTOResponse;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.PacienteRepository;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;
import Proyecto.Backend.DWI.Security.JwtService;
import Proyecto.Backend.DWI.Security.UserDetailsImpl;
import jakarta.transaction.Transactional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthDTOResponse registrarPaciente(RegistrarDTORequest request){
        if (usuarioRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("Error: El DNI ya está registrado.");
        }

        Usuario nuevUsuario = new Usuario();
        nuevUsuario.setDni(request.getDni());
        nuevUsuario.setCorreo(request.getCorreo()); // Ahora guarda el correo en Usuario también
        nuevUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevUsuario.setRol("PACIENTE");
        Usuario usuarioGuardado = usuarioRepository.save(nuevUsuario);

        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsuarioId(usuarioGuardado);
        nuevoPaciente.setNombre(request.getNombre());
        nuevoPaciente.setApellido(request.getApellido());
        nuevoPaciente.setCorreo(request.getCorreo());
        nuevoPaciente.setTelefono(request.getTelefono());

        pacienteRepository.save(nuevoPaciente);

        String jwtToken = jwtService.generateToken(new UserDetailsImpl(usuarioGuardado));
        return new AuthDTOResponse(jwtToken, usuarioGuardado.getRol(), usuarioGuardado.getId());    
    }

    public AuthDTOResponse iniciarSesion(IniciarSesionDTORequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getIdentificador(), request.getPassword())
        );

        Usuario usuario = request.getIdentificador().contains("@") 
            ? usuarioRepository.findByCorreo(request.getIdentificador()).orElseThrow(() -> new RuntimeException("Admin no encontrado"))
            : usuarioRepository.findByDni(request.getIdentificador()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        String jwtToken = jwtService.generateToken(new UserDetailsImpl(usuario));
        return new AuthDTOResponse(jwtToken, usuario.getRol(), usuario.getId());    
    }
}
