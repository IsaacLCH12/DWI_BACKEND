package Proyecto.Backend.DWI.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cambiado a nullable = true porque el administrador no usará DNI
    @Column(nullable = true, length = 8, unique = true)
    private String dni;

    // Nueva columna para el inicio de sesión administrativo o médico
    @Column(nullable = true, unique = true)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol;

    public Usuario() {
    }

    public Usuario(Long id, String dni, String correo, String password, String rol) {
        this.id = id;
        this.dni = dni;
        this.correo = correo;
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

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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