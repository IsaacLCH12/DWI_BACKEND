package Proyecto.Backend.DWI.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false,length = 120)
    private String direccion;

    @Column(nullable = false,length = 15)
    private String telefonoContacto;

    @Column(nullable = false)
    private boolean estado;

    
}
