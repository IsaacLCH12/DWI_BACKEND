package Proyecto.Backend.DWI.Models;

import java.time.LocalDateTime;

public class Cita {
    private Long id;
    private Long pacienteId;
    private Long servicioId;
    private String pacienteNombre;
    private String especialidad;
    private LocalDateTime fechaHora;
    private String estado;

    public Cita() {
    }


    public Cita(Long id, Long pacienteId, Long servicioId, String pacienteNombre, String especialidad, LocalDateTime fechaHora, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.servicioId = servicioId;
        this.pacienteNombre = pacienteNombre;
        this.especialidad = especialidad;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return this.pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getServicioId() {
        return this.servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getPacienteNombre() {
        return this.pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   


}
