package Proyecto.Backend.DWI.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.HorariosMedicosDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.HorariosMedicosDTOresponse;
import Proyecto.Backend.DWI.Services.HorarioMedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/horarios")
public class HorarioMedicosController {
    private final HorarioMedicoService horariosService;

    public HorarioMedicosController(HorarioMedicoService horariosService) {
        this.horariosService = horariosService;
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<HorariosMedicosDTOresponse>> obtenerHorariosPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(horariosService.buscarPorMedico(medicoId));
    }

    // Solo ADMIN: Crear un nuevo horario
    @PostMapping
    public ResponseEntity<?> crearHorario(@Valid @RequestBody HorariosMedicosDTORequest request) {
        try {
            HorariosMedicosDTOresponse nuevoHorario = horariosService.crearHorario(request);
            return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Solo ADMIN: Eliminar un horario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Long id) {
        try {
            horariosService.eliminarHorario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
