package Proyecto.Backend.DWI.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aunque pongamos nullable = true, si la DB ya tiene NOT NULL, el error persistirá
    @Column(nullable = true, length = 8) 
    private String dni;

    @Column(nullable = true, unique = true)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol;

    public Usuario() {}

    public Usuario(String dni, String correo, String password, String rol) {
        this.dni = dni;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
