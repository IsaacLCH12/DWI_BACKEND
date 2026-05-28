package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Dtos.Request.MedicoDTORequest;
import Proyecto.Backend.DWI.Dtos.Response.MedicoDTOResponse;
import Proyecto.Backend.DWI.Models.Medico;
import Proyecto.Backend.DWI.Models.Sede;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.MedicoRepository;
import Proyecto.Backend.DWI.Repositories.SedeRepository;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SedeRepository sedeRepository;

    public MedicoService(MedicoRepository medicoRepository,
                         UsuarioRepository usuarioRepository,
                         SedeRepository sedeRepository) {

        this.medicoRepository = medicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.sedeRepository = sedeRepository;
    }

    // READ: Listar médicos
    public List<MedicoDTOResponse> obtenerTodos() {

        List<Medico> medicosEntity = medicoRepository.findAll();

        return mapearListaMedicos(medicosEntity);
    }

    // CREATE: Registrar médico
    @Transactional
    public MedicoDTOResponse crearMedico(MedicoDTORequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() ->
                        new RuntimeException("Sede no encontrada"));

        Medico nuevoMedico = new Medico();

        nuevoMedico.setUsuarioId(usuario);
        nuevoMedico.setNombre(request.getNombre());
        nuevoMedico.setApellido(request.getApellido());
        nuevoMedico.setFotoUrl(request.getFotoUrl());
        nuevoMedico.setSedeId(sede);

        Medico medicoGuardado =
                medicoRepository.save(nuevoMedico);

        return convertirADTOResponse(medicoGuardado);
    }

    // UPDATE: Actualizar médico
    @Transactional
    public MedicoDTOResponse actualizarMedico(
            Long id,
            MedicoDTORequest request) {

        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Médico no encontrado con el ID: " + id));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        Sede sede = sedeRepository.findById(request.getSedeId())
                .orElseThrow(() ->
                        new RuntimeException("Sede no encontrada"));

        medicoExistente.setUsuarioId(usuario);
        medicoExistente.setNombre(request.getNombre());
        medicoExistente.setApellido(request.getApellido());
        medicoExistente.setFotoUrl(request.getFotoUrl());
        medicoExistente.setSedeId(sede);

        Medico medicoActualizado =
                medicoRepository.save(medicoExistente);

        return convertirADTOResponse(medicoActualizado);
    }

    // Métodos auxiliares
    private List<MedicoDTOResponse> mapearListaMedicos(
            List<Medico> medicosEntity) {

        List<MedicoDTOResponse> listaResponse =
                new ArrayList<>();

        for (Medico m : medicosEntity) {

            listaResponse.add(convertirADTOResponse(m));
        }

        return listaResponse;
    }

    private MedicoDTOResponse convertirADTOResponse(
            Medico medico) {

        MedicoDTOResponse response =
                new MedicoDTOResponse();

        response.setId(medico.getId());
        response.setNombre(medico.getNombre());
        response.setApellido(medico.getApellido());
        response.setFotoUrl(medico.getFotoUrl());

        response.setNombreSede(
                medico.getSedeId().getNombre());

        return response;
    }
}