package Proyecto.Backend.DWI.Dtos.Response;

public class AuthDTOResponse {
    
    private String token;
    private String rol;
    private Long usuarioId;

    public AuthDTOResponse(String token, String rol, Long usuarioId) {
        this.token = token;
        this.rol = rol;
        this.usuarioId = usuarioId;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}