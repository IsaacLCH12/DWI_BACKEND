package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;

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

    // READ: Listar todos los servicios
    public List<ServicioDTOResponse> obtenerTodos() {

        List<Servicio> serviciosEntity = servicioRepository.findAll();

        return mapearListaServicios(serviciosEntity);
    }

    // CREATE: Registrar servicio
    @Transactional
    public ServicioDTOResponse crearServicio(ServicioDTORequest request) {

        Servicio nuevoServicio = new Servicio();

        nuevoServicio.setNombre(request.getNombre());
        nuevoServicio.setDescripcion(request.getDescripcion());
        nuevoServicio.setPrecio(request.getPrecio());
        nuevoServicio.setDuracionMin(request.getDuracionMin());
        nuevoServicio.setEstado(request.getEstado());

        Servicio servicioGuardado =
                servicioRepository.save(nuevoServicio);

        return convertirADTOResponse(servicioGuardado);
    }

    // UPDATE: Actualizar servicio
    @Transactional
    public ServicioDTOResponse actualizarServicio(
            Long id,
            ServicioDTORequest request) {

        Servicio servicioExistente = servicioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Servicio no encontrado con el ID: " + id));

        servicioExistente.setNombre(request.getNombre());
        servicioExistente.setDescripcion(request.getDescripcion());
        servicioExistente.setPrecio(request.getPrecio());
        servicioExistente.setDuracionMin(request.getDuracionMin());
        servicioExistente.setEstado(request.getEstado());

        Servicio servicioActualizado =
                servicioRepository.save(servicioExistente);

        return convertirADTOResponse(servicioActualizado);
    }

    // Métodos auxiliares
    private List<ServicioDTOResponse> mapearListaServicios(
            List<Servicio> serviciosEntity) {

        List<ServicioDTOResponse> listaResponse =
                new ArrayList<>();

        for (Servicio s : serviciosEntity) {

            listaResponse.add(convertirADTOResponse(s));
        }

        return listaResponse;
    }

    private ServicioDTOResponse convertirADTOResponse(
            Servicio servicio) {

        ServicioDTOResponse response =
                new ServicioDTOResponse();

        response.setId(servicio.getId());
        response.setNombre(servicio.getNombre());
        response.setDescripcion(servicio.getDescripcion());
        response.setPrecio(servicio.getPrecio());
        response.setDuracionMin(servicio.getDuracionMin());
        response.setEstado(servicio.getEstado());

        return response;
    }
}