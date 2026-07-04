package Proyecto.Backend.DWI.Configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import Proyecto.Backend.DWI.Security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;
    private final UserDetailsService detailsServiceImpl;

    public SecurityConfig(JwtAuthenticationFilter authenticationFilter, UserDetailsService detailsServiceImpl) {
        this.authenticationFilter = authenticationFilter;
        this.detailsServiceImpl = detailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(jakarta.servlet.DispatcherType.ERROR).permitAll()
                        /* 1. RUTAS 100% PÚBLICAS (No piden token) */
                        .requestMatchers(
                                "/api/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()
                        /* 2. CATÁLOGOS DE LECTURA PÚBLICA (El paciente ve esto para armar su cita) */
                        .requestMatchers(HttpMethod.GET,
                                "/api/sedes/activas",
                                "/api/servicios/activos",
                                "/api/medicos/filtrar",
                                "/api/horarios/medico/**")
                        .permitAll()

                        /* 3. LECTURAS PRIVADAS */
                        .requestMatchers(HttpMethod.GET, "/api/pacientes", "/api/pagos").hasRole("ADMIN")

                        /* 4. CRUD ADMINISTRATIVO */
                        .requestMatchers("/api/sedes/**", "/api/servicios/**", "/api/medicos/**", "/api/horarios/**")
                        .hasRole("ADMIN")

                        /* 5. TODO LO DEMÁS REQUIERE ESTAR LOGUEADO (Ej: Pagar, Ver Perfil) */
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(detailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 💡 2. PEGA ESTE BLOQUE AL FINAL DE LA CLASE
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Aquí pones las URLs que tienen permiso de entrar
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "https://tu-frontend.vercel.app")); 
        // Permitir todos los métodos (incluyendo OPTIONS, que es el que te está fallando)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
