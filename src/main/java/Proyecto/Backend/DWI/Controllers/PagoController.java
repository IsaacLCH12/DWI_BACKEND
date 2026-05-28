package Proyecto.Backend.DWI.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Dtos.Request.PagoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.PagoDTOResponse;
import Proyecto.Backend.DWI.Services.PagoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTOResponse>> listarPagos() {

        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<?> crearPago(
            @Valid @RequestBody PagoDTORequest request) {

        try {

            PagoDTOResponse nuevoPago =
                    pagoService.crearPago(request);

            return new ResponseEntity<>(
                    nuevoPago,
                    HttpStatus.CREATED);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(
            @PathVariable Long id,
            @Valid @RequestBody PagoDTORequest request) {

        try {

            PagoDTOResponse pagoActualizado =
                    pagoService.actualizarPago(id, request);

            return ResponseEntity.ok(pagoActualizado);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}