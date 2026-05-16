package Proyecto.Backend.DWI.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacienteId",nullable = false)
    private Paciente pacienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="servicioId",nullable = false)
    private Servicio servicioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicoId",nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "sedeId", nullable = false)
    private Sede sede;
    
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, length = 20)
    private String estado; /** "pendiente","completado","cancelado" */

    public Cita() {
    }


    public Cita(Long id, Paciente pacienteId, Servicio servicioId, Medico medico, Sede sede, LocalDateTime fechaHora, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.servicioId = servicioId;
        this.medico = medico;
        this.sede = sede;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPacienteId() {
        return this.pacienteId;
    }

    public void setPacienteId(Paciente pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Servicio getServicioId() {
        return this.servicioId;
    }

    public void setServicioId(Servicio servicioId) {
        this.servicioId = servicioId;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Sede getSede() {
        return this.sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
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
