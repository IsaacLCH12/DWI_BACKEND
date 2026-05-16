package Proyecto.Backend.DWI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación idéntica al estándar de tu compañera: Objeto completo, carga perezosa y no nulo
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuarioId; 

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = true) // El teléfono en tu diagrama no especifica restricciones, puede ser opcional
    private String telefono;

    // --- CONSTRUCTOR VACÍO (Obligatorio para JPA) ---
    public Paciente() {
    }

    // --- CONSTRUCTOR CON PARÁMETROS ---
    public Paciente(Long id, Usuario usuarioId, String nombre, String apellido, String correo, String telefono) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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