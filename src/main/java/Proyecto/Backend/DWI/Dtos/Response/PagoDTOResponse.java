package Proyecto.Backend.DWI.Dtos.Response;

import java.time.LocalDateTime;

public class PagoDTOResponse {

    private Long id;
    private Double montoTotal;
    private String metodoPago;
    private String estado;
    private LocalDateTime fechaPago;
    private String nombrePaciente;

    public PagoDTOResponse() {
    }

    public PagoDTOResponse(Long id, Double montoTotal,
                           String metodoPago, String estado,
                           LocalDateTime fechaPago,
                           String nombrePaciente) {

        this.id = id;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.nombrePaciente = nombrePaciente;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombrePaciente() {
        return this.nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}