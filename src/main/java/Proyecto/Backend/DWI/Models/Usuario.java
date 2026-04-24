package Proyecto.Backend.DWI.Models;

public class Usuario {
    private Long id;
    private String dni;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(Long id, String dni, String password, String rol) {
        this.id = id;
        this.dni = dni;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
