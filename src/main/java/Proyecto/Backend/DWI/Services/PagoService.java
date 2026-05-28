package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Dtos.Request.PagoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.PagoDTOResponse;
import Proyecto.Backend.DWI.Models.Cita;
import Proyecto.Backend.DWI.Models.Pago;
import Proyecto.Backend.DWI.Repositories.CitaRepository;
import Proyecto.Backend.DWI.Repositories.PagoRepository;
import jakarta.transaction.Transactional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final CitaRepository citaRepository;

    public PagoService(PagoRepository pagoRepository,
                       CitaRepository citaRepository) {

        this.pagoRepository = pagoRepository;
        this.citaRepository = citaRepository;
    }

    // READ: Listar pagos
    public List<PagoDTOResponse> obtenerTodos() {

        List<Pago> pagosEntity = pagoRepository.findAll();

        return mapearListaPagos(pagosEntity);
    }

    // CREATE: Registrar pago
    @Transactional
    public PagoDTOResponse crearPago(PagoDTORequest request) {

        Cita cita = citaRepository.findById(request.getCitaId())
                .orElseThrow(() ->
                        new RuntimeException("Cita no encontrada"));

        Pago nuevoPago = new Pago();

        nuevoPago.setCitaId(cita);
        nuevoPago.setMontoTotal(request.getMontoTotal());
        nuevoPago.setMetodoPago(request.getMetodoPago());
        nuevoPago.setEstado(request.getEstado());
        nuevoPago.setFechaPago(request.getFechaPago());

        Pago pagoGuardado = pagoRepository.save(nuevoPago);

        return convertirADTOResponse(pagoGuardado);
    }

    // UPDATE: Actualizar pago
    @Transactional
    public PagoDTOResponse actualizarPago(
            Long id,
            PagoDTORequest request) {

        Pago pagoExistente = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pago no encontrado con el ID: " + id));

        Cita cita = citaRepository.findById(request.getCitaId())
                .orElseThrow(() ->
                        new RuntimeException("Cita no encontrada"));

        pagoExistente.setCitaId(cita);
        pagoExistente.setMontoTotal(request.getMontoTotal());
        pagoExistente.setMetodoPago(request.getMetodoPago());
        pagoExistente.setEstado(request.getEstado());
        pagoExistente.setFechaPago(request.getFechaPago());

        Pago pagoActualizado =
                pagoRepository.save(pagoExistente);

        return convertirADTOResponse(pagoActualizado);
    }

    // Métodos auxiliares
    private List<PagoDTOResponse> mapearListaPagos(
            List<Pago> pagosEntity) {

        List<PagoDTOResponse> listaResponse =
                new ArrayList<>();

        for (Pago p : pagosEntity) {

            listaResponse.add(convertirADTOResponse(p));
        }

        return listaResponse;
    }

    private PagoDTOResponse convertirADTOResponse(Pago pago) {

        PagoDTOResponse response =
                new PagoDTOResponse();

        response.setId(pago.getId());
        response.setMontoTotal(pago.getMontoTotal());
        response.setMetodoPago(pago.getMetodoPago());
        response.setEstado(pago.getEstado());
        response.setFechaPago(pago.getFechaPago());

        response.setNombrePaciente(
                pago.getCitaId()
                    .getPacienteId()
                    .getNombre()
                + " "
                + pago.getCitaId()
                    .getPacienteId()
                    .getApellido());

        return response;
    }
}