package Proyecto.Backend.DWI.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Models.Sede;
import Proyecto.Backend.DWI.Services.SedeService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sedes")
public class SedeController {
    private final SedeService sedeService;


    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    /*endpoint publico */
    @GetMapping("/activas")
    public ResponseEntity<List<Sede>> listarActivas() {
        return ResponseEntity.ok(sedeService.obtenerActivas());
    }

    /*endpoints protegidos */

    @GetMapping
    public ResponseEntity<List<Sede>> listarTodas() {
        return ResponseEntity.ok(sedeService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<Sede> crear(@RequestBody Sede sede) {
        return ResponseEntity.ok(sedeService.guardar(sede));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> actualizar(@PathVariable Long id, @RequestBody Sede sede) {
        return ResponseEntity.ok(sedeService.actualizar(id, sede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitar(@PathVariable Long id) {
        sedeService.deshabilitarSede(id);
        return ResponseEntity.noContent().build();
    }
}
