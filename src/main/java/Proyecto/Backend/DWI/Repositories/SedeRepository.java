package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Proyecto.Backend.DWI.Models.Sede;

public interface SedeRepository extends JpaRepository<Sede,Long>{
    
    /*JPQL para sedes activas */
    @Query("SELECT s FROM Sede s WHERE s.estado =true")
    List<Sede> findByEstadoTrue();
}
