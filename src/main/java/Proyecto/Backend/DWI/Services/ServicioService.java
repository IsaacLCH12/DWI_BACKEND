package Proyecto.Backend.DWI.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Dtos.Request.ServicioDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.ServicioDTOResponse;
import Proyecto.Backend.DWI.Models.Servicio;
import Proyecto.Backend.DWI.Repositories.ServicioRepository;
import jakarta.transaction.Transactional;


@Service
public class ServicioService {
    
    private final ServicioRepository servicioRepository;


    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<ServicioDTOResponse> obtenerTodos(){
        return servicioRepository.findAll().stream()
        .map(this::convertirAResponse)
        .collect(Collectors.toList());
    }

    public List<ServicioDTOResponse> obtenerActivos(){
        return servicioRepository.buscarServiciosActivos().stream()
        .map(this::convertirAResponse)
        .collect(Collectors.toList());
    }

    @Transactional
    public ServicioDTOResponse guardar(ServicioDTORequest request){
        Servicio servicio = new Servicio();
        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setPrecio(request.getPrecio());
        servicio.setDuracionMin(request.getDuracionMin());
        servicio.setEstado(request.getEstado());

        return convertirAResponse(servicioRepository.save(servicio));
    }

    @Transactional
    public ServicioDTOResponse actualizar(Long id,ServicioDTORequest request){
        Servicio servicio = servicioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("SERVICIO NO ENCONTRADO"));
        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setPrecio(request.getPrecio());
        servicio.setDuracionMin(request.getDuracionMin());
        servicio.setEstado(request.getEstado());
        
        return convertirAResponse(servicioRepository.save(servicio));
    }

    @Transactional
    public void deshabilitar(Long id){
        Servicio servicio = servicioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("SERVICIO NO ENCONTRADO"));
        servicio.setEstado(false);
        servicioRepository.save(servicio);
    }


    // --- MÉTODO PRIVADO PARA MAPEAR ---
    private ServicioDTOResponse convertirAResponse(Servicio servicio) {
        return new ServicioDTOResponse(
                servicio.getId(),
                servicio.getNombre(),
                servicio.getDescripcion(),
                servicio.getPrecio(),
                servicio.getDuracionMin(),
                servicio.getEstado()
        );
    }
}
