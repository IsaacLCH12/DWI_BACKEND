package Proyecto.Backend.DWI.Controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Services.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;

    }

    @GetMapping
    public List<Paciente> listar() {

        return pacienteService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.crearPaciente(paciente);
        if (nuevoPaciente == null) {
            return ResponseEntity.badRequest().build(); // 400 Si el DNI está duplicado
        }
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED); // 201 Creado

    }
}