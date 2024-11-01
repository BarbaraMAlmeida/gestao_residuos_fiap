package fiap.com.br.atvd_spring_boot.config.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity)
            throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //enndpoints de registro e login são liberados
                        .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()

                        //Métodos get, só estarão liberados se estiver autenticado e for user ou admin
                        .requestMatchers(HttpMethod.GET, "/**")
                        .hasAnyRole("ADMIN", "USER")

                        //Endpoints put, delete e post, somente abertos para Admin autenticado.
                        .requestMatchers(
                                HttpMethod.POST, "/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**")
                        .hasRole("ADMIN")

                        //Qualquer outro endpoint necessita de autenticação
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
