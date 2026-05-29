package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    
    /*jpql cuando el paciente elige sede y servicio*/
   @Query("SELECT m FROM Medico m WHERE m.sedeId.id = :sedeId AND m.servicioId.id = :servicioId AND m.estado = true")
    List<Medico> filtrarParaCita(
            @Param("sedeId") Long sedeId, 
            @Param("servicioId") Long servicioId
    );
}
