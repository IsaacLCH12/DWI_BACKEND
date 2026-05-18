package Proyecto.Backend.DWI.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.Pago;


public interface PagoRepository extends JpaRepository<Pago,Long>{
    
        /*jpql para buscar pagos por estado */
        @Query("SELECT p FROM Pago p WHERE p.estado = :estado")
        List<Pago> buscarPorEstado(@Param("estado") String estado);

        /*jpql para buscar el pago asociado a una cita medica en especifico */
        @Query("SELECT p FROM Pago p WHERE p.citaId =: citaId")
        Optional<Pago> buscarPorCitaId(@Param("citaId") Long citaId);
    }
