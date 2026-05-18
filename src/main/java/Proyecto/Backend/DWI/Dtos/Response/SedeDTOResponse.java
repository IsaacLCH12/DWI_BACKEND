package Proyecto.Backend.DWI.Dtos.Response;

public class SedeDTOResponse {
    
    private Long id;
    private String nombre;
    private String direccion;
    private String telefonoContacto;
    private boolean estado;


    public SedeDTOResponse() {
    }

    public SedeDTOResponse(Long id, String nombre, String direccion, String telefonoContacto, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonoContacto = telefonoContacto;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return this.telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
