package Proyecto.Backend.DWI.Security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import Proyecto.Backend.DWI.Models.Usuario;

public class UserDetailsImpl implements UserDetails {
    
    private final Usuario usuario;


    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override   
    public Collection<? extends GrantedAuthority> getAuthorities(){
        /*aca decimos q rol tiene este usuario */
        return List.of(new SimpleGrantedAuthority("ROLE_"+ usuario.getRol()));
    }

    @Override
    public String getPassword(){
        return usuario.getPassword();
    }

    @Override
    public String getUsername(){
        return usuario.getDni();
    }

    public Usuario getUsuario(){
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
