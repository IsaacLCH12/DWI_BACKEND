package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Models.Servicio;



@Service
public class ServicioService {
    
    private List<Servicio> servicioDB = new ArrayList<>();
    private Long generadorId = 1L;

    public List<Servicio> obtenerTodos(){
        return servicioDB;
    }
/*Get por id */
    public Optional<Servicio> obtenerPorId(Long id){
        return servicioDB.stream()
        .filter(servicio -> servicio.getId().equals(id))
        .findFirst();
    }

/* POST */

    public Servicio crearServicio(Servicio nuevoServicio){
        if (nuevoServicio.getPrecio() < 0) {
            System.out.println("Error: El precio del servicio no puede ser negativo.");
            return null; // Rechaza la creación
        }
        nuevoServicio.setId(generadorId++);
        servicioDB.add(nuevoServicio);
        return nuevoServicio;
    }

    /*PUT */
    public Servicio actualizarServicio(Servicio servicioActualizado, Long id){
       for(Servicio servicio : servicioDB){
        if(servicio.getId().equals(id)){
            servicio.setEspecialidad(servicioActualizado.getEspecialidad());
            servicio.setDescripcion(servicioActualizado.getDescripcion());
            servicio.setPrecio(servicioActualizado.getPrecio());
            return servicio;
        }
       } 
       return null;
    }
    /*DELETE */
    public boolean eliminarServicio(Long id){
        return servicioDB.removeIf(servicio -> servicio.getId().equals(id));
        
    }
}
