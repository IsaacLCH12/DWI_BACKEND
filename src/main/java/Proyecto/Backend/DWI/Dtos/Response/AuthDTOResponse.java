package Proyecto.Backend.DWI.Dtos.Response;

public class AuthDTOResponse {
    
    private String token;

    public AuthDTOResponse() {}

    public AuthDTOResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
