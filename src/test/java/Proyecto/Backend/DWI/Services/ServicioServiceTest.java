package Proyecto.Backend.DWI.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import Proyecto.Backend.DWI.Models.Servicio;

public class ServicioServiceTest {
    @Test
    void debeCrearServicioCorrectamente() {
        ServicioService service = new ServicioService();
        Servicio nuevo = new Servicio();
        nuevo.setEspecialidad("Cardiología");
        nuevo.setPrecio(150.0);

        Servicio resultado = service.crearServicio(nuevo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cardiología", resultado.getEspecialidad());
    }

    @Test
    void debeRechazarServicioSiPrecioEsNegativo() {
        ServicioService service = new ServicioService();
        Servicio nuevo = new Servicio();
        nuevo.setEspecialidad("Rayos X");
        nuevo.setPrecio(-50.0); // Precio inválido

        Servicio resultado = service.crearServicio(nuevo);

        // Esperamos que el sistema devuelva null (o rechace la creación)
        assertNull(resultado, "El sistema no debe permitir registrar servicios con precio negativo");
    }
}
