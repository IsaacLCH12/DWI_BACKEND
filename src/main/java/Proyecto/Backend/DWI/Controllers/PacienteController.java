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

import Proyecto.Backend.DWI.Dtos.Request.PacienteRequestDTO;
import Proyecto.Backend.DWI.Dtos.Response.PacienteDTOResponse;
import Proyecto.Backend.DWI.Services.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    
   private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> listar() {
        return ResponseEntity.ok(pacienteService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDTOResponse> crear(@Valid @RequestBody PacienteRequestDTO request) {
        return new ResponseEntity<>(pacienteService.crearPaciente(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO request) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, request));
    }
}