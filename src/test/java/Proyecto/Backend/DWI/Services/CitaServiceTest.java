package Proyecto.Backend.DWI.Services;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import Proyecto.Backend.DWI.Models.Cita;

public class CitaServiceTest {
    

    // Prueba TDD: Validación de campos obligatorios
    @Test
    void debeRechazarCitaSiFechaHoraEsNulaOVacida() {
        
        CitaService citaService = new CitaService(null, null);
        
        Cita nuevaCita = new Cita();
        nuevaCita.setPacienteId(1L);
        nuevaCita.setServicioId(1L);
        nuevaCita.setFechaHora(null); 
        
        Cita resultado = citaService.crearCita(nuevaCita);

        // Esperamos que el sistema aborte y devuelva null
        assertNull(resultado, "El sistema debe rechazar una cita que no tenga fecha y hora");
    }
}
