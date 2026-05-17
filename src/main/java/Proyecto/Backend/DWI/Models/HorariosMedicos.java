package Proyecto.Backend.DWI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import java.time.LocalTime;

@Entity
@Table(name = "horariosMedico") // Nombre idéntico al del diagrama
public class HorariosMedicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación idéntica al estándar: Un médico tiene muchos horarios
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicoId", nullable = false)
    private Medico medicoId; 

    @Column(nullable = false)
    private Integer diaSemana; // En tu diagrama es de tipo Integer

    @Column(nullable = false)
    private LocalTime horaInicio; // En tu diagrama es LocalTime

    @Column(nullable = false)
    private LocalTime horaFin; // En tu diagrama es LocalTime

    // --- CONSTRUCTOR VACÍO ---
    public HorariosMedicos() {
    }

    // --- CONSTRUCTOR LLENO ---
    public HorariosMedicos(Long id, Medico medicoId, Integer diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Medico medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}