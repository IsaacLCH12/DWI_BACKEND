package Proyecto.Backend.DWI.Models;

import java.time.LocalDateTime;

public class Cita {
    private Long id;
    private String pacienteNombre;
    private String especialidad;
    private LocalDateTime fechaHora;
    private String estado;

    public Cita() {
    }

    public Cita(Long id, String pacienteNombre, String especialidad,
            LocalDateTime fechaHora, String estado) {

        this.id = id;
        this.pacienteNombre = pacienteNombre;
        this.especialidad = especialidad;
        this.estado = estado;
        this.fechaHora = fechaHora;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id= id;}    
public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}
