package Proyecto.Backend.DWI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Backend.DWI.Models.Sede;

public interface SedeRepository extends JpaRepository<Sede,Long>{
    
    List<Sede> findByEstadoTrue();
}
