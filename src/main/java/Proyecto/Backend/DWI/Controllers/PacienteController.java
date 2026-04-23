package Proyecto.Backend.DWI.Controllers;

import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService; // Inyección de dependencias

    @PostMapping("/registro")
    public Paciente registrar(@RequestBody Paciente paciente) {
        return pacienteService.registrarPaciente(paciente);
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.obtenerTodos();
    }
}