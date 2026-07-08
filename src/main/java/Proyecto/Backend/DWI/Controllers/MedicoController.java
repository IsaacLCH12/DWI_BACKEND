package Proyecto.Backend.DWI.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Proyecto.Backend.DWI.Dtos.Request.MedicoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.MedicoDTOResponse;
import Proyecto.Backend.DWI.Services.MedicoService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // RUTAS PÚBLICAS (Para los pacientes en Angular)
      
     /* Devuelve los doctores activos 
     que atienden una especialidad en una sede específica.
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<MedicoDTOResponse>> filtrar(
            @RequestParam Long sedeId, 
            @RequestParam Long servicioId) {
        return ResponseEntity.ok(medicoService.filtrarParaCita(sedeId, servicioId));
    }

    // RUTAS PRIVADAS (Solo para el Administrador)
        /* 
      Devuelve absolutamente todos los médicos registrados.
     */
    @GetMapping
    public ResponseEntity<List<MedicoDTOResponse>> listarTodos() {
        return ResponseEntity.ok(medicoService.obtenerTodos());
    }

    /*
    Registra un nuevo médico (requiere enviar usuarioId, sedeId y servicioId en el JSON).
     */
    @PostMapping
    public ResponseEntity<MedicoDTOResponse> crear(@Valid @RequestBody MedicoDTORequest request) {
        return ResponseEntity.ok(medicoService.crearMedico(request));
    }

    /* 
      Actualiza los datos de un médico existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody MedicoDTORequest request) {
        return ResponseEntity.ok(medicoService.actualizarMedico(id, request));
    }

    /* 
     Alterna el estado de un médico (Activo/Inactivo).
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id) {
        medicoService.cambiarEstadoMedico(id);
        return ResponseEntity.noContent().build(); // Devuelve un código 204 (Éxito sin contenido)
    }
}