package Proyecto.Backend.DWI.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto.Backend.DWI.Models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    boolean existsByDni(String dni);
    Optional<Usuario> findByDni(String dni);
    
    // Nuevos métodos necesarios para gestionar las cuentas por correo
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);
}