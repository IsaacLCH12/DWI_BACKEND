package Proyecto.Backend.DWI.Dtos.Response;

import java.time.LocalDateTime;

public class CitaDTOResponse {
    
    private Long id;
    private LocalDateTime fechaHora;
    private String estadoCita;
    private String nombrePaciente;
    private String nombreServicio;
    private String nombreMedico;
    private String nombreSede;


    public CitaDTOResponse() {
    }

    public CitaDTOResponse(Long id, LocalDateTime fechaHora, String estadoCita, String nombrePaciente, String nombreServicio, String nombreMedico, String nombreSede) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estadoCita = estadoCita;
        this.nombrePaciente = nombrePaciente;
        this.nombreServicio = nombreServicio;
        this.nombreMedico = nombreMedico;
        this.nombreSede = nombreSede;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstadoCita() {
        return this.estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getNombrePaciente() {
        return this.nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreServicio() {
        return this.nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreMedico() {
        return this.nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getNombreSede() {
        return this.nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

}
