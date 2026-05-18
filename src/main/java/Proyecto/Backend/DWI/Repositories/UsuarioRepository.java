package Proyecto.Backend.DWI.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Backend.DWI.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    // Spring Data JPA crea la consulta SQL automáticamente solo con leer el nombre del método
    boolean existsByDni(String dni);
    Optional<Usuario> findByDni(String dni);
    
}
