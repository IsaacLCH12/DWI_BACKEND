package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SedeDTORequest {
    
    @NotBlank(message = "El nombre de la sede es obligatoria")
    private String nombre;

    @NotBlank(message = "La direccion de la sede es obligatoria")
    private String direccion;

    @NotBlank(message = "El telefono de contacto de la sede es obligatoria")
    private String telefonoContacto;

    @NotNull(message = "Debe especificar si la sede esta activa o inactiva")
    private boolean estado;



    public SedeDTORequest() {
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return this.telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public boolean isEstado() {
        return this.estado;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
