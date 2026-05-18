package Proyecto.Backend.DWI.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioDTORequest {
    
    @NotBlank(message = "El DNI es obligatorio")
     @Pattern(regexp = "^[0-9]{8}$",message = "El DNI debe tener exactamente 8 numeros")
    private String dni;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;


    public UsuarioDTORequest() {
    }

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

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
