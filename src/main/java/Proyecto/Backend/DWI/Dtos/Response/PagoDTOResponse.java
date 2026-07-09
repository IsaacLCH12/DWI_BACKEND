package Proyecto.Backend.DWI.Dtos.Response;

import java.time.LocalDateTime;

public class PagoDTOResponse {

    private Long id;
    // 💡 SOLUCIÓN: Agregamos el ID de la cita para que Angular lo reciba
    private Long citaId; 
    
    private Double montoTotal;
    private String metodoPago;
    private String numeroOperacion; // 💡 Agregado para que se vea en la tabla de Angular
    private String estado;
    private LocalDateTime fechaPago;
    private String nombrePaciente;

    public PagoDTOResponse() {
    }

    public PagoDTOResponse(Long id, Long citaId, Double montoTotal,
                           String metodoPago, String numeroOperacion, String estado,
                           LocalDateTime fechaPago,
                           String nombrePaciente) {
        this.id = id;
        this.citaId = citaId;
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.numeroOperacion = numeroOperacion;
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

    public Long getCitaId() {
        return citaId;
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

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
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