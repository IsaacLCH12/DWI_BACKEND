package Proyecto.Backend.DWI.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Proyecto.Backend.DWI.Dtos.Request.ServicioDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.ServicioDTOResponse;
import Proyecto.Backend.DWI.Models.Servicio;
import Proyecto.Backend.DWI.Repositories.ServicioRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void debeCrearServicioCorrectamente() {
        // ARRANGE
        ServicioDTORequest request = new ServicioDTORequest();
        request.setNombre("Cardiología");
        request.setDescripcion("Revisión general");
        request.setPrecio(150.0);
        request.setDuracionMin(30);
        request.setEstado(true);

        Servicio servicioMock = new Servicio();
        servicioMock.setId(1L);
        servicioMock.setNombre("Cardiología");
        servicioMock.setPrecio(150.0);
        servicioMock.setEstado(true);

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioMock);

        // ACT
        ServicioDTOResponse resultado = servicioService.guardar(request);

        // ASSERT
        assertNotNull(resultado);
        assertEquals("Cardiología", resultado.getNombre());
    }

    @Test
    void debeRechazarServicioSiPrecioEsNegativo() {
        // ARRANGE
        ServicioDTORequest request = new ServicioDTORequest();
        request.setNombre("Rayos X");
        request.setDescripcion("Placas");
        request.setPrecio(-50.0); // Precio inválido
        request.setDuracionMin(20);
        request.setEstado(true);

        // ACT
        Set<ConstraintViolation<ServicioDTORequest>> violaciones = validator.validate(request);

        // ASSERT
        assertFalse(violaciones.isEmpty(), "El sistema no debe permitir registrar servicios con precio negativo");
        
        boolean errorEnPrecio = violaciones.stream().anyMatch(v -> v.getPropertyPath().toString().equals("precio"));
        assertTrue(errorEnPrecio, "El error detectado debe ser del campo precio");
    }
}