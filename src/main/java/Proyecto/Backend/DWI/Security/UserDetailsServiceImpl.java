package Proyecto.Backend.DWI.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
        Usuario usuario;

        // Comprobamos si el identificador contiene un arroba para saber qué consulta ejecutar
        if (identificador.contains("@")) {
            usuario = usuarioRepository.findByCorreo(identificador)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con correo " + identificador + " no existe."));
        } else {
            usuario = usuarioRepository.findByDni(identificador)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con DNI " + identificador + " no existe."));
        }

        return new UserDetailsImpl(usuario);
    }
}