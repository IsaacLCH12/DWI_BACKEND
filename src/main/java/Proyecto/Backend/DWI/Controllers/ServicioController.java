package Proyecto.Backend.DWI.Controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.ServicioDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.ServicioDTOResponse;
import Proyecto.Backend.DWI.Services.ServicioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // --- PÚBLICO: Para que el Front-End llene sus selects ---
    @GetMapping("/activos")
    public ResponseEntity<List<ServicioDTOResponse>> listarActivos() {
        return ResponseEntity.ok(servicioService.obtenerActivos());
    }

    // --- PRIVADOS: Solo Administradores ---
    @GetMapping
    public ResponseEntity<List<ServicioDTOResponse>> listarTodos() {
        return ResponseEntity.ok(servicioService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<ServicioDTOResponse> crear(@Valid @RequestBody ServicioDTORequest request) {
        return ResponseEntity.ok(servicioService.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTOResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ServicioDTORequest request) {
        return ResponseEntity.ok(servicioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitar(@PathVariable Long id) {
        servicioService.deshabilitar(id);
        return ResponseEntity.noContent().build(); // 204 No Content es lo ideal para Delete
    }
}