package Proyecto.Backend.DWI.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import Proyecto.Backend.DWI.Models.Usuario;
import Proyecto.Backend.DWI.Repositories.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String correoAdmin = "admin@clinicaromero.com";

        // Comprobamos si ya existe el registro para evitar inserciones duplicadas al reiniciar
        if (!usuarioRepository.existsByCorreo(correoAdmin)) {
            
            Usuario admin = new Usuario();
            admin.setCorreo(correoAdmin);
            admin.setPassword(passwordEncoder.encode("AdminClinica2026!"));
            admin.setRol("ADMIN"); 
            
            usuarioRepository.save(admin);
            
            System.out.println("=================================================");
            System.out.println("🚀 CUENTA ADMINISTRATIVA INICIALIZADA CON ÉXITO");
            System.out.println("📧 Identificador: " + correoAdmin);
            System.out.println("🔒 Credencial: AdminClinica2026!");
            System.out.println("=================================================");
        }
    }
}