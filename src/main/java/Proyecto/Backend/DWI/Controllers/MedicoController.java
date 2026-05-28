package Proyecto.Backend.DWI.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.MedicoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.MedicoDTOResponse;
import Proyecto.Backend.DWI.Services.MedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTOResponse>> listarMedicos() {

        return ResponseEntity.ok(medicoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<?> crearMedico(
            @Valid @RequestBody MedicoDTORequest request) {

        try {

            MedicoDTOResponse nuevoMedico =
                    medicoService.crearMedico(request);

            return new ResponseEntity<>(
                    nuevoMedico,
                    HttpStatus.CREATED);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMedico(
            @PathVariable Long id,
            @Valid @RequestBody MedicoDTORequest request) {

        try {

            MedicoDTOResponse medicoActualizado =
                    medicoService.actualizarMedico(id, request);

            return ResponseEntity.ok(medicoActualizado);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}