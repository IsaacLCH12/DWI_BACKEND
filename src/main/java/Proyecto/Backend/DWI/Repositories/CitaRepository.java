package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.Cita;

public interface CitaRepository extends JpaRepository<Cita,Long> {
    /*IMPLEMENTACION DE JPQL */

    /*BUSCAR CITAS POR EL ESTADO */
    @Query("SELECT c FROM Cita c WHERE c.estado = :estado")
    List<Cita> buscarPorEstado(@Param("estado") String estado);

    /*Buscar todas las citas de un paciente en especifico */
    @Query("SELECT c FROM Cita c WHERE c.paciente.id= :pacienteId")
    List<Cita> buscarPorPacienteId(@Param("pacienteId") Long pacienteId);
}
