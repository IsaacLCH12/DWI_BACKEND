package Proyecto.Backend.DWI.Controllers;


public class ServicioController {

    // /* public final ServicioService servicioService;

    // public ServicioController(ServicioService servicioService){
    //     this.servicioService = servicioService;
    // }

    // /*Get  Listar*/
    // @GetMapping
    // public List<Servicio> listarTodos(){
    //     return servicioService.obtenerTodos();
    // }
    
    // /*Get por id */
    // @GetMapping("/{id}")
    // public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id){
    //     return servicioService.obtenerPorId(id)
    //         .map(servicio -> ResponseEntity.ok(servicio))
    //         .orElse(ResponseEntity.notFound().build());
    // }

    // /*Post */
    // @PostMapping
    // public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio){
    //     Servicio nuevoServicio = servicioService.crearServicio(servicio);
    //     return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    // }
    // /*PUT */
    // @PutMapping("/{id}")
    // public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio detalleServicio){
    //     Servicio servicioActualizado = servicioService.actualizarServicio(detalleServicio, id);

    //     if(servicioActualizado != null){
    //         return ResponseEntity.ok(servicioActualizado);
    //     }
    //     return ResponseEntity.notFound().build();
    // }
    // /*DELETE */
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> eliminar(@PathVariable Long id){
    //     if(servicioService.eliminarServicio(id)){
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }
 

 }