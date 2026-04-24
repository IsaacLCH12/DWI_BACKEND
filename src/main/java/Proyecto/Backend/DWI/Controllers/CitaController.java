package Proyecto.Backend.DWI.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Models.Cita;
import Proyecto.Backend.DWI.Services.CitaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/citas")
public class CitaController {
    
    public final CitaService citaService;

    

    public CitaController(CitaService citaService){
        this.citaService= citaService;
    }


    /*GET: listar */
    @GetMapping
    public List<Cita> ListarTodas() {
        return citaService.obtenerTodas();
    }

    /*GET: obtener por id */
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerId(@PathVariable Long id) {
        return citaService.obtenerPorId(id)
                .map(cita -> ResponseEntity.ok(cita))
                .orElse(ResponseEntity.notFound().build());
    }
    
    /*POST: crear una cita */
    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        Cita nuevaCita = citaService.crearCita(cita);
        /*un manejo de error por si le falata agregar una casilla */
        if (nuevaCita == null) {
            return ResponseEntity.badRequest().build(); // Devuelve Error 400
        }
        return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
    }

    /*PUT: actualizar cita */
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita detalleCita) {
       Cita citaActualizada = citaService.actualizarCita(detalleCita, id);
       if (citaActualizada !=null) {
        return ResponseEntity.ok(citaActualizada);
       }
        
        return ResponseEntity.notFound().build();
    }

    /*DELETE: eliminar la cita */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if ( citaService.eliminarCita(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
