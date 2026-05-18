package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Proyecto.Backend.DWI.Models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio,Long> {
    
    /*Jpql para obtener solo los servicios que estan activos */
    @Query("SELECT s FROM Servicio s WHERE s.estado =true")
    List<Servicio> buscarServiciosActivos();
}
