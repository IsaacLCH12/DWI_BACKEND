package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotBlank;

public class IniciarSesionDTORequest {
    
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
