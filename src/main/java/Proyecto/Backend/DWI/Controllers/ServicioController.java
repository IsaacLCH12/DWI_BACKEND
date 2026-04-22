package Proyecto.Backend.DWI.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto.Backend.DWI.Models.Servicio;
import Proyecto.Backend.DWI.Services.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    public final ServicioService servicioService;

    public ServicioController(ServicioService servicioService){
        this.servicioService = servicioService;
    }

    /*Get  Listar*/
    @GetMapping
    public List<Servicio> listarTodos(){
        return servicioService.obtenerTodos();
    }
    
    /*Get por id */
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id){
        return servicioService.obtenerPorId(id)
            .map(servicio -> ResponseEntity.ok(servicio))
            .orElse(ResponseEntity.notFound().build());
    }

    /*Post */
    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio){
        Servicio nuevoServicio = servicioService.crearServicio(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }
    /*PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio detalleServicio){
        Servicio servicioActualizado = servicioService.actualizarServicio(detalleServicio, id);

        if(servicioActualizado != null){
            return ResponseEntity.ok(servicioActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    /*DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(servicioService.eliminarServicio(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


 }