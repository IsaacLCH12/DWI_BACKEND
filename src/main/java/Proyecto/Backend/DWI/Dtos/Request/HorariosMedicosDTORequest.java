package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.time.LocalTime;

public class HorariosMedicosDTORequest {

    @NotNull(message = "El medicoId es obligatorio para asignar el horario.")
    private Long medicoId;

    @NotNull(message = "El día de la semana es obligatorio.")
    @Min(value = 1, message = "El día de la semana debe ser mínimo 1 (Lunes).")
    @Max(value = 7, message = "El día de la semana debe ser máximo 7 (Domingo).")
    private Integer diaSemana;

    @NotNull(message = "La hora de inicio es obligatoria.")
    private LocalTime HoraInicio; // Usamos prefijo o directo según lo manejen

    @NotNull(message = "La hora de fin es obligatoria.")
    private LocalTime HoraFin;

    // --- CONSTRUCTOR VACÍO ---
    public HorariosMedicosDTORequest() {
    }

    // --- CONSTRUCTOR LLENO ---
    public HorariosMedicosDTORequest(Long medicoId, Integer diaSemana, LocalTime javaHoraInicio, LocalTime javaHoraFin) {
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.HoraInicio = javaHoraInicio;
        this.HoraFin = javaHoraFin;
    }

    // --- GETTERS Y SETTERS ---
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

    public LocalTime getJavaHoraInicio() {
        return HoraInicio;
    }

    public void setJavaHoraInicio(LocalTime javaHoraInicio) {
        this.HoraInicio = javaHoraInicio;
    }

    public LocalTime getJavaHoraFin() {
        return HoraFin;
    }

    public void setJavaHoraFin(LocalTime javaHoraFin) {
        this.HoraFin = javaHoraFin;
    }
}S