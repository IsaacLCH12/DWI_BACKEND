package Proyecto.Backend.DWI.Dtos.Response;

public class MedicoDTOResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String fotoUrl;
    private String nombreSede;

    public MedicoDTOResponse() {
    }

    public MedicoDTOResponse(Long id, String nombre, String apellido, String fotoUrl, String nombreSede) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fotoUrl = fotoUrl;
        this.nombreSede = nombreSede;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFotoUrl() {
        return this.fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getNombreSede() {
        return this.nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }
}