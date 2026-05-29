package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoDTORequest {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La foto es obligatoria")
    private String fotoUrl;

    @NotNull(message = "La sede es obligatoria")
    private Long sedeId;

    @NotNull(message = "El servicio (especialidad) es obligatorio")
    private Long servicioId;

    public MedicoDTORequest() {}

    public Long getUsuarioId() { return this.usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return this.apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getFotoUrl() { return this.fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public Long getSedeId() { return this.sedeId; }
    public void setSedeId(Long sedeId) { this.sedeId = sedeId; }

    public Long getServicioId() { return this.servicioId; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }
}