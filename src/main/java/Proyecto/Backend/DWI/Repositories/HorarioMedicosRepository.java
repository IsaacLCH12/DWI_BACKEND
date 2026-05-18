package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.HorariosMedicos;

public interface HorarioMedicosRepository extends JpaRepository<HorariosMedicos,Long>{

    /*jpql para buscar todos los horarios de un medico en especifico ordenados por dia */
    @Query("SELECT h FROM HorariosMedicos h WHERE h.medicoId.id = :medicoId ORDER BY h.diaSemana ASC")
    List<HorariosMedicos> buscarPorMedicoId(@Param("medicoId") Long medicoId);
}
