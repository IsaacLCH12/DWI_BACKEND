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
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByDni(dni)
        .orElseThrow(()-> new UsernameNotFoundException("El usuario con DNI "+dni+" no existe."));
    return new UserDetailsImpl(usuario);
    }

}
