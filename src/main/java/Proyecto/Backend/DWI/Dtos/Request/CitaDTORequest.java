package Proyecto.Backend.DWI.Dtos.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class CitaDTORequest {
    
    @NotNull (message = "El paciente es obligatorio")
    private Long pacienteId;

    @NotNull (message = "El servicio es obligatorio")
    private Long servicioId;

    @NotNull (message = "El medico es obligatorio")
    private Long medicoId;

    @NotNull (message = "La sede es obligatoria")
    private Long sedeId;

    @NotNull (message = "La fecha y hora es obligatoria")
    @FutureOrPresent(message = "La cita no puede ser en el pasado")
    private LocalDateTime fechaHora;


    public CitaDTORequest() {
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

    public Long getMedicoId() {
        return this.medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getSedeId() {
        return this.sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public LocalDateTime getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
