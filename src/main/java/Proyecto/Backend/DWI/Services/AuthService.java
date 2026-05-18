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

    /*REGISTRO TRANSACCIONAL: crear usuario y luego paciente */
    @Transactional
    public AuthDTOResponse registrarPaciente(RegistrarDTORequest request){

        /*1. Validar duplicados */
        if (usuarioRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("Error: El DNI ya esta registrado en el sistema.");
        }

        /*2. Crear la cuenta de seguridad */
        Usuario nuevUsuario = new Usuario();
        nuevUsuario.setDni(request.getDni());
        /*encriptamos la contra */
        nuevUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevUsuario.setRol("PACIENTE");
        Usuario usuarioGuardado = usuarioRepository.save(nuevUsuario);

        /*3. Crear el Perfil del paciente vinculado al usuario */
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsuarioId(usuarioGuardado);
        nuevoPaciente.setNombre(request.getNombre());
        nuevoPaciente.setApellido(request.getApellido());
        nuevoPaciente.setCorreo(request.getCorreo());
        nuevoPaciente.setTelefono(request.getTelefono());

        /*4. Guardar paciente */
        pacienteRepository.save(nuevoPaciente);

        /*generamos token */
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(usuarioGuardado));
        return new AuthDTOResponse(jwtToken);
    }
    
    /*INICIAR SESION */
    public AuthDTOResponse iniciarSesion(IniciarSesionDTORequest request){

        /*verificacion si la contraseña encriptada coincide */
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getDni(), request.getPassword())
        );

        /*si las credenciales de la linea anterior son correctas: */
        Usuario usuario = usuarioRepository.findByDni(request.getDni())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(new UserDetailsImpl(usuario));
        return new AuthDTOResponse(jwtToken);
    }
}
