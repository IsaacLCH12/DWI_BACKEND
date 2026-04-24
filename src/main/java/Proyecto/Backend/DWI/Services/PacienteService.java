package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Proyecto.Backend.DWI.Models.Paciente;

@Service
public class PacienteService {

    /* nuesta base de datos temporal */
    private List<Paciente> pacienteDB = new ArrayList<>();
    private Long generadorId = 1L;

    /* GET LISTAR */
    public List<Paciente> obtenerTodas() {
        return pacienteDB;
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteDB.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Crear paciente (Validando que nadie tenga el mismo correo)
    public Paciente crearPaciente(Paciente nuevoPaciente) {

        
        boolean correoExiste = pacienteDB.stream()
                .anyMatch(p -> p.getCorreo().equals(nuevoPaciente.getCorreo()));

        if (correoExiste) {
            System.out.println("Error: El correo ya está asignado a otro perfil.");
            return null; // Rechaza si el DNI ya está registrado
        }

        nuevoPaciente.setId(generadorId++);
        pacienteDB.add(nuevoPaciente);
        return nuevoPaciente;
    }

}
