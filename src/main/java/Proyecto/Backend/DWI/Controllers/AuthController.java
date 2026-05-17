package Proyecto.Backend.DWI.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Models.Login;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.RegistroPaciente;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Services.AuthService;
import Proyecto.Backend.DWI.Services.PacienteService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

private final AuthService authService;
private final PacienteService pacienteService;


    public AuthController(AuthService authService, PacienteService pacienteService) {
        this.authService = authService;
        this.pacienteService= pacienteService;
    }

    // ENDPOINT 1: Registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroPaciente registroPaciente) {
       
       
       /*Crear la Cuenta de Seguridad modo usuario */
       Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setDni(registroPaciente.getDni());
        nuevoUsuario.setPassword(registroPaciente.getPassword());
        nuevoUsuario.setRol("PACIENTE");

        Usuario usuarioCreado = authService.registrar(nuevoUsuario);

        if (usuarioCreado== null) {
            return ResponseEntity.badRequest().body("Error: El dni ya esta registrado");
        }
        /*Crear el perfil del paciente */
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsuarioId(usuarioCreado); 
        nuevoPaciente.setNombre(registroPaciente.getNombre());
        nuevoPaciente.setApellido(registroPaciente.getApellido());
        nuevoPaciente.setCorreo(registroPaciente.getCorreo());
        nuevoPaciente.setTelefono(registroPaciente.getTelefono());

        Paciente pacientecreado= pacienteService.crearPaciente(nuevoPaciente);

        if (pacientecreado==null) {
            return ResponseEntity.badRequest().body("Error: El dni ya esta registrado");
        }
        return new ResponseEntity<>(pacientecreado,HttpStatus.CREATED);
    }       

    // ENDPOINT 2: Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login credenciales) {
        Usuario usuarioLogueado = authService.login(credenciales.getDni(), credenciales.getPassword());
        
        if (usuarioLogueado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 No autorizado
        }
        return ResponseEntity.ok(usuarioLogueado); // 200 OK
    }
}


