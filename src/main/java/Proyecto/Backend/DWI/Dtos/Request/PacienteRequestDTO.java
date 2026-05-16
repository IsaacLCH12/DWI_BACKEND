package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PacienteRequestDTO {

    @NotNull(message = "El usuarioId es obligatorio para asociar el paciente.")
    private Long usuarioId;

    // Validación: Solo números y exactamente 8 dígitos
    @NotBlank(message = "El DNI es obligatorio.")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números.")
    private String dni;

    // Validación: Solo letras (incluye espacios para nombres compuestos y tildes)
    @NotBlank(message = "El nombre es obligatorio.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre debe contener solo letras.")
    private String nombre;

    // Validación: Solo letras (incluye espacios y tildes)
    @NotBlank(message = "El apellido es obligatorio.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido debe contener solo letras.")
    private String apellido;

    // Validación: Formato de correo electrónico válido
    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El formato del correo electrónico no es válido.")
    private String correo;

    // Validación: Solo números y exactamente 9 dígitos
    @NotBlank(message = "El teléfono es obligatorio.")
    @Size(min = 9, max = 9, message = "El teléfono debe tener exactamente 9 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números.")
    private String telefono;

    // --- CONSTRUCTOR VACÍO ---
    public PacienteRequestDTO() {
    }

    // --- CONSTRUCTOR LLENO ---
    public PacienteRequestDTO(Long usuarioId, String dni, String nombre, String apellido, String correo, String telefono) {
        this.usuarioId = usuarioId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    // --- GETTERS Y SETTERS ---
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}