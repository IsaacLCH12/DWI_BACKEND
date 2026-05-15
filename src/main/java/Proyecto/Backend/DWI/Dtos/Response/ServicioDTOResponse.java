package Proyecto.Backend.DWI.Dtos.Response;

public class ServicioDTOResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer duracionMin;
    private Boolean estado;

    public ServicioDTOResponse() {
    }

    public ServicioDTOResponse(Long id, String nombre,
                               String descripcion, Double precio,
                               Integer duracionMin, Boolean estado) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMin = duracionMin;
        this.estado = estado;
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDuracionMin() {
        return this.duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}