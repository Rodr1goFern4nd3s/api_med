package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    //Classe de configurações de segurança

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
        /*
        Desabilitamos o processo padrão do spring security, o que ele oferecia de controle de autenticação baseado no formulario, sessão e cooking
        agora o processo é stateless. Agora temos que configurar o processo de autenticação no projeto.
         */
    }

    @Bean //Ensinando o Spring como injeta objetos com essa anotação
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        /*A classe AuthenticationConfiguration tem esse metodo getAuthenticationManager() que sabe criar
          o objeto AuthenticationManager
         */
        return configuration.getAuthenticationManager();
    }

    @Bean
    //Ensinando o Spring a usar esse algoritmo(BCrypt) de hashing de senha
    public PasswordEncoder passwordEncoder() {
        //PasswordEncoder é a classe que representa o algoritmo de hashing de senha
        //BCryptPasswordEncoder() classe do Spring e essa conseguimos instanciar como se fosse classe java tradicional
        return new BCryptPasswordEncoder();
    }
}
