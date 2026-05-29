package Proyecto.Backend.DWI.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Dtos.Request.HorariosMedicosDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.HorariosMedicosDTOresponse;
import Proyecto.Backend.DWI.Models.HorariosMedicos;
import Proyecto.Backend.DWI.Models.Medico;
import Proyecto.Backend.DWI.Repositories.HorarioMedicosRepository;
import Proyecto.Backend.DWI.Repositories.MedicoRepository;
import jakarta.transaction.Transactional;

@Service
public class HorarioMedicoService {
    private final HorarioMedicosRepository horarioRepository;
    private final MedicoRepository medicoRepository;

    public HorarioMedicoService(HorarioMedicosRepository horarioRepository, MedicoRepository medicoRepository) {
        this.horarioRepository = horarioRepository;
        this.medicoRepository = medicoRepository;
    }

    // READ: Ver los horarios de un doctor específico ordenados por día
    public List<HorariosMedicosDTOresponse> buscarPorMedico(Long medicoId) {
        return horarioRepository.buscarPorMedicoId(medicoId).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // CREATE: Asignar un nuevo bloque de horario a un doctor
    @Transactional
    public HorariosMedicosDTOresponse crearHorario(HorariosMedicosDTORequest request) {
        // 1. Validamos que el médico exista
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + request.getMedicoId()));

        // 2. Opcional pero recomendado: Validar que la hora inicio sea antes que la hora fin
        if (request.getJavaHoraInicio().isAfter(request.getJavaHoraFin())) {
            throw new RuntimeException("La hora de inicio no puede ser después de la hora de fin");
        }

        // 3. Crear y guardar
        HorariosMedicos nuevoHorario = new HorariosMedicos();
        nuevoHorario.setMedicoId(medico); 
        nuevoHorario.setDiaSemana(request.getDiaSemana());
        nuevoHorario.setHoraInicio(request.getJavaHoraInicio()); 
        nuevoHorario.setHoraFin(request.getJavaHoraFin());

        HorariosMedicos horarioGuardado = horarioRepository.save(nuevoHorario);

        return convertirAResponse(horarioGuardado);
    }

    // DELETE: Quitar un bloque de horario 
    @Transactional
    public void eliminarHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new RuntimeException("Horario no encontrado");
        }
        horarioRepository.deleteById(id);
    }

    private HorariosMedicosDTOresponse convertirAResponse(HorariosMedicos horario) {
        return new HorariosMedicosDTOresponse(
                horario.getId(),
                horario.getMedicoId().getId(), // Extraemos el número ID del objeto Medico
                horario.getDiaSemana(),
                horario.getHoraInicio(),
                horario.getHoraFin()
        );
    }
}
