package Proyecto.Backend.DWI.Dtos.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagoDTORequest {

    @NotNull(message = "La cita es obligatoria")
    private Long citaId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double montoTotal;

    @NotNull(message = "El metodo de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDateTime fechaPago;

    public PagoDTORequest() {
    }

    public Long getCitaId() {
        return this.citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Double getMontoTotal() {
        return this.montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return this.metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}