package Proyecto.Backend.DWI.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Models.Sede;
import Proyecto.Backend.DWI.Repositories.SedeRepository;
import jakarta.transaction.Transactional;

@Service
public class SedeService {
    
    private final SedeRepository sedeRepository;


    public SedeService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    /*Listar sedes */
    public List<Sede> obtenerTodas(){
        return sedeRepository.findAll();
    }

    /*para el usuario ver las disponibles */
    public List<Sede> obtenerActivas(){
        return sedeRepository.findByEstadoTrue();
    }

    @Transactional
    public Sede guardar(Sede sede){
        return sedeRepository.save(sede);
    }

    @Transactional
    public Sede actualizar(Long id, Sede datosNuevos){
        Sede sedeExistente = sedeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        sedeExistente.setNombre(datosNuevos.getNombre());
        sedeExistente.setDireccion(datosNuevos.getDireccion());
        sedeExistente.setTelefonoContacto(datosNuevos.getTelefonoContacto());
        return sedeRepository.save(sedeExistente);
    }

    @Transactional
    public void deshabilitarSede(Long id){
        Sede sede = sedeRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Sede no encontrada"));
        sede.setEstado(false);
        sedeRepository.save(sede);
    }
}
