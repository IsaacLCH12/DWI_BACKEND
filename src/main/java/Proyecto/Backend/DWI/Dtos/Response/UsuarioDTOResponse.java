package Proyecto.Backend.DWI.Dtos.Response;

public class UsuarioDTOResponse {
    

    private Long id;
    private String dni;
    private String rol;


    public UsuarioDTOResponse() {
    }


    public UsuarioDTOResponse(Long id, String dni, String rol) {
        this.id = id;
        this.dni = dni;
        this.rol = rol;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
