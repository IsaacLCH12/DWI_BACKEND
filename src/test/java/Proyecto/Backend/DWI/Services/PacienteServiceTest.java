package Proyecto.Backend.DWI.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Proyecto.Backend.DWI.Dtos.Request.PacienteRequestDTO;
import Proyecto.Backend.DWI.Dtos.Response.PacienteDTOResponse;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.PacienteRepository;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class) // Habilita Mockito
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository; // Simulamos la BD

    @Mock
    private UsuarioRepository usuarioRepository; // Simulamos la BD

    @InjectMocks
    private PacienteService pacienteService; // Inyectamos los simulacros en tu servicio real

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Preparamos el Validador de Spring para probar las reglas de tus DTOs
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void debeCrearPacienteSiTelefonoEsCorrecto() {
        // 1. ARRANGE (Preparar los datos usando tus DTOs)
        PacienteRequestDTO request = new PacienteRequestDTO(1L, "12345678", "Stefany", "Lopez", "stefany@mail.com", "987654321");
        
        Usuario usuarioMock = new Usuario(1L, "12345678", "pass", "PACIENTE");
        Paciente pacienteMock = new Paciente(1L, usuarioMock, "Stefany", "Lopez", "stefany@mail.com", "987654321");

        // Le decimos a Mockito qué responder cuando el servicio llame a la BD
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(pacienteRepository.existsByCorreo("stefany@mail.com")).thenReturn(false);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteMock);

        // 2. ACT (Ejecutar el servicio)
        PacienteDTOResponse resultado = pacienteService.crearPaciente(request);

        // 3. ASSERT (Verificar resultados)
        assertNotNull(resultado, "El paciente debería crearse exitosamente");
        assertEquals("987654321", resultado.getTelefono());
    }

    @Test
    void debeRechazarPacienteSiTelefonoNoTiene9Digitos() {
        // 1. ARRANGE (Enviamos un teléfono de 5 dígitos)
        PacienteRequestDTO request = new PacienteRequestDTO(1L, "12345678", "Isaac", "Perez", "isaac@mail.com", "98761");

        // 2. ACT (Validamos el DTO tal como lo haría el Controlador)
        Set<ConstraintViolation<PacienteRequestDTO>> violaciones = validator.validate(request);

        // 3. ASSERT (Verificamos que Spring bloquee el objeto antes de llegar al servicio)
        assertFalse(violaciones.isEmpty(), "El sistema debe rechazar un teléfono que no tenga 9 dígitos");
        
        boolean errorEnTelefono = violaciones.stream().anyMatch(v -> v.getPropertyPath().toString().equals("telefono"));
        assertTrue(errorEnTelefono, "El error reportado debe pertenecer al campo teléfono");
    }

    @Test
    void generarClaveAdmin() {
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = 
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        
        System.out.println("TU HASH PARA ADMIN123 ES: " + encoder.encode("admin123"));
    }
}