package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Models.Cita;
import Proyecto.Backend.DWI.Models.Paciente;
import Proyecto.Backend.DWI.Models.Servicio;

@Service
public class CitaService {

    /*nuesta base de datos temporal */
    private List<Cita> citaDB = new ArrayList<>();
    private Long generadorId = 1L;

    //inyectamos los servicios de los demas
    private final PacienteService pacienteService;
    private final ServicioService servicioService;

public CitaService(PacienteService pacienteService, ServicioService servicioService) {
        this.pacienteService = pacienteService;
        this.servicioService = servicioService;
    }
/*GET: obtener la lista total */
public List<Cita> obtenerTodas(){
    return citaDB;
}

/*GET: para obtener las cita por id */
public Optional<Cita> obtenerPorId(Long id){
    return citaDB.stream()
    .filter(cita -> cita.getId().equals(id))
    .findFirst();
}

/*POST: crear una nueva cita */
public Cita crearCita(Cita nuevaCita){

    Optional<Paciente> pacienteEncontrado = pacienteService.obtenerPorId(nuevaCita.getPacienteId());
    if (pacienteService.obtenerPorId(nuevaCita.getPacienteId()).isEmpty()) {
            System.out.println("Error: El paciente con ID " + nuevaCita.getPacienteId() + " no existe.");
            return null; // Detenemos la creación
        }
Optional<Servicio> servicioEncontrado = servicioService.obtenerPorId(nuevaCita.getServicioId());
        if (servicioService.obtenerPorId(nuevaCita.getServicioId()).isEmpty()) {
            System.out.println("Error: El servicio médico con ID " + nuevaCita.getServicioId() + " no existe.");
            return null; // Detenemos la creación
        }

        Paciente paciente = pacienteEncontrado.get();
        Servicio servicio = servicioEncontrado.get();

        nuevaCita.setPacienteNombre(paciente.getNombre() + " " + paciente.getApellido());
        nuevaCita.setEspecialidad(servicio.getEspecialidad());


    /*damos id y sumamos 1 con cada nueva cita */
    nuevaCita.setId(generadorId++);
    /*si no mandan estado, se considera pendiente */
    if (nuevaCita.getEstado()==null || nuevaCita.getEstado().isEmpty()) {
       nuevaCita.setEstado("PENDIENTE"); 
    }
    citaDB.add(nuevaCita);
    return nuevaCita;
}

/*PUT: actulizar cita existente */
public Cita actualizarCita(Cita citaActualizada, Long id){
for(Cita cita: citaDB){
    if (cita.getId().equals(id)) {
        cita.setPacienteNombre(citaActualizada.getPacienteNombre());
        cita.setEspecialidad(citaActualizada.getEspecialidad());
        cita.setFechaHora(citaActualizada.getFechaHora());
        cita.setEstado(citaActualizada.getEstado());
        return cita;
    }
}
return null;
}

/*DELETE: eliminar una cita :D */
public boolean eliminarCita(Long id){
    return citaDB.removeIf(cita -> cita.getId().equals(id));
}

}
