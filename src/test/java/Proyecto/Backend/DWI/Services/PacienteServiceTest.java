package Proyecto.Backend.DWI.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import Proyecto.Backend.DWI.Models.Paciente;

public class PacienteServiceTest {
    

    @Test
    void debeCrearPacienteSiTelefonoEsCorrecto(){
  
    PacienteService service = new PacienteService();
        Paciente nuevo = new Paciente();
        nuevo.setNombre("Stefany");
        nuevo.setApellido("Lopez");
        nuevo.setTelefono("987654321"); // Teléfono exacto de 9 dígitos
        
        Paciente resultado = service.crearPaciente(nuevo);

        assertNotNull(resultado, "El paciente debería crearse exitosamente");
        assertEquals("987654321", resultado.getTelefono());
    }

    // La validación TDD (Rechazar Teléfono inválido)
    @Test
    void debeRechazarPacienteSiTelefonoNoTiene9Digitos() {
        PacienteService service = new PacienteService();
        Paciente nuevo = new Paciente();
        nuevo.setNombre("Isaac");
        nuevo.setApellido("Perez");
        nuevo.setTelefono("987615"); // Teléfono inválido (solo 5 dígitos)
        
        Paciente resultado = service.crearPaciente(nuevo);

        // Esperamos que el sistema devuelva null bloqueando el registro
        assertNull(resultado, "El sistema debe rechazar un teléfono que no tenga 9 dígitos");
    }
}
