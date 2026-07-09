package Proyecto.Backend.DWI.Dtos.Response;

public class PacienteDTOResponse {

    private Long id;
    private Long usuarioId; // Muestra el ID del usuario asociado según el diagrama
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String dni;

    // --- CONSTRUCTOR VACÍO ---
    public PacienteDTOResponse() {
    }


    public PacienteDTOResponse(Long id, Long usuarioId, String nombre, String apellido, String correo, String telefono, String dni) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.dni = dni;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
}