package Proyecto.Backend.DWI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto.Backend.DWI.Models.Usuario;

@Service
public class AuthService {
    
private List<Usuario> usuariosDB = new ArrayList<>();
    private Long generadorId = 1L;

    //para buscar por Id
    public Optional<Usuario> obtenerPorId(Long id) {
    return usuariosDB.stream().filter(u -> u.getId().equals(id)).findFirst();
}

    // Lógica para REGISTRO
    public Usuario registrar(Usuario nuevoUsuario) {
        // Validar si el correo ya existe
        boolean dniExiste = usuariosDB.stream()
                .anyMatch(u -> u.getDni().equals(nuevoUsuario.getDni()));

        if (dniExiste) {
            return null; // Rechazar registro
        }

        // Si no mandan rol, por defecto es PACIENTE
        if(nuevoUsuario.getRol() == null || nuevoUsuario.getRol().isEmpty()){
            nuevoUsuario.setRol("PACIENTE");
        }

        nuevoUsuario.setId(generadorId++);
        usuariosDB.add(nuevoUsuario);
        return nuevoUsuario;
    }

    /*______________________________________________________________ */
    // Lógica para LOGIN
    public Usuario login(String dni, String password) {
        // Buscar al usuario por correo
        Optional<Usuario> usuarioEncontrado = usuariosDB.stream()
                .filter(u -> u.getDni().equals(dni))
                .findFirst();

        // Si existe y la contraseña es igual, logueo exitoso
        if (usuarioEncontrado.isPresent() && usuarioEncontrado.get().getPassword().equals(password)) {
            return usuarioEncontrado.get();
        }

        return null; // Credenciales incorrectas
    }
}
