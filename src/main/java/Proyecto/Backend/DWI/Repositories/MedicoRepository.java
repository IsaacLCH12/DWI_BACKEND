package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    
    /*jpql para buscar medico por sede */
    @Query("SELECT m FROM Medico m WHERE m.sedeId.id = :sedeId")
    List<Medico> buscarPorSedeId(@Param("sedeId") Long sedeId);
}
