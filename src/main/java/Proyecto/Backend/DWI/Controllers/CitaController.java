package Proyecto.Backend.DWI.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.CitaDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.CitaDTOResponse;
import Proyecto.Backend.DWI.Services.CitaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<?> crearCita(@Valid @RequestBody CitaDTORequest request) {
        try {
            CitaDTOResponse nuevaCita = citaService.crearCita(request);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CitaDTOResponse>> listarCitas() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Evaluamos si el token contiene el rol del administrador
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(citaService.obtenerTodasLasCitas());
        }
        return ResponseEntity.ok(citaService.obtenerMisCitas());
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        try {
            CitaDTOResponse citaCancelada = citaService.cancelarCita(id);
            return ResponseEntity.ok(citaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    
}

@PutMapping("/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Long id, @Valid @RequestBody CitaDTORequest request) {
        try {
            CitaDTOResponse citaActualizada = citaService.actualizarCita(id, request);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}