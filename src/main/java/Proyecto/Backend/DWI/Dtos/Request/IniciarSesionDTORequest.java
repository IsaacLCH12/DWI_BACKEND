package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotBlank;

public class IniciarSesionDTORequest {
    
    // Cambiamos el nombre a "identificador" para que acepte DNI o Correo
    @NotBlank(message = "El DNI o Correo es obligatorio")
    private String identificador;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}