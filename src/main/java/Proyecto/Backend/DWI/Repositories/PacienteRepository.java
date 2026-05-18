package Proyecto.Backend.DWI.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Proyecto.Backend.DWI.Models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{
    
    /*jpql para buscar paciente por el id de su usuario vinculado( cuando se loguea) */
    @Query("SELECT p FROM Paciente p WHERE p.usuarioId.id = :usuarioId")
    Optional<Paciente> buscarPorUsuarioId(@Param("usuarioId") Long usuarioId);

    /*jpql para buscar paciente por nombre o apellido */
     @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR LOWER(p.apellido) LIKE LOWER(CONCAT('%', :termino,'%'))")
     List<Paciente> buscarPorNombreOApellido(@Param("termino") String termino);

     // Busca un Paciente entrando a su relación Usuario y comparando el DNI
    @Query("SELECT p FROM Paciente p WHERE p.usuario.dni = :dni")
    Optional<Paciente> buscarPorUsuarioDni(@Param("dni") String dni);
    }
