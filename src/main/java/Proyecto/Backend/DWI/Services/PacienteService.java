package Proyecto.Backend.DWI.Services;

import Proyecto.Backend.DWI.Models.Paciente;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {
    private List<Paciente> pacientes = new ArrayList<>();

    public Paciente registrarPaciente(Paciente paciente) {
        // Aquí podrías validar que el DNI no esté repetido
        pacientes.add(paciente);
        return paciente;
    }

    public List<Paciente> obtenerTodos() {
        return pacientes;
    }
}