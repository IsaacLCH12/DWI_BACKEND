package Proyecto.Backend.DWI.Models;

public class Servicio {
    private Long id;
    private String especialidad;
    private String descripcion;
    private double precio;

    public Servicio() {
    }

    public Servicio(Long id, String especialidad, String descripcion, double precio){
        this.id = id;
        this.especialidad = especialidad;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    
}
