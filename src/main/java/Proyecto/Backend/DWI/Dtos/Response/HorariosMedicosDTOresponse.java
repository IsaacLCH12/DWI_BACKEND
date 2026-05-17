package Proyecto.Backend.DWI.Dtos.Response;

import java.time.LocalTime;

public class HorariosMedicosDTOResponse {

    private Long id;
    private Long medicoId; // Solo devolvemos la FK limpia numéricamente
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // --- CONSTRUCTOR VACÍO ---
    public HorariosMedicosDTOResponse() {
    }

    // --- CONSTRUCTOR LLENO ---
    public HorariosMedicosDTOResponse(Long id, Long medicoId, Integer diaSemana, LocalTime horaInicio, LocalTime horaFin) {
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

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
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