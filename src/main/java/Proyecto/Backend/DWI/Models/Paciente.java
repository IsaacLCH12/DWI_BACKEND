package Proyecto.Backend.DWI.Models;

public class Paciente {
    private String dni;
    private String nombre;
    private String apellido;
    private String gmail;
    private String password;

    public Paciente() {}

    public Paciente(String dni, String nombre, String apellido, String gmail, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
        this.password = password;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}