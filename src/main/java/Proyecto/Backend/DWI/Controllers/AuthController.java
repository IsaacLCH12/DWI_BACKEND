package Proyecto.Backend.DWI.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.IniciarSesionDTORequest;
import Proyecto.Backend.DWI.Dtos.Request.RegistrarDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.AuthDTOResponse;
import Proyecto.Backend.DWI.Services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistrarDTORequest request) {
        try {
            AuthDTOResponse response = authService.registrarPaciente(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());    
        }
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<AuthDTOResponse> iniciarSesion(@Valid @RequestBody IniciarSesionDTORequest request) {
        
        AuthDTOResponse response = authService.iniciarSesion(request);
        return ResponseEntity.ok(response);
    }
    
    
}


