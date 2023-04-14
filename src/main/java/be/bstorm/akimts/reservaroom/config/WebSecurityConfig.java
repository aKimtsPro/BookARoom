package be.bstorm.akimts.reservaroom.config;

import be.bstorm.akimts.reservaroom.models.entity.User;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Scanner;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Les premiers matchers ont la priorité
     * anyRequest, s'il est mis, doit être le dernier matcher
     *
     * <h2>RequestMatchers:</h2>
     *
     *      <h3>Lambda RequestMatchers:</h3>
     *      <ul>
     *          <li>prend une HttpServletRequest en param, renvoi un boolean</li>
     *      </ul>
     *
     *      <h3>Method:</h3>
     *      <ul>
     *          <li>une valeur de l'enum HttpMethod</li>
     *      </ul>
     *
     *      <h3>Pattern: 1 ou pls chaine de carac. représentant des URIs</h3>
     *      <ul>
     *          <li>? : remplace une lettre</li>
     *          <li>* : n'importe quelles valeurs dans 1 segment</li>
     *          <li>{machin:regex} : n'imp. quelles valeurs correspondant au pattern regex pour 1 segment</li>
     *          <li>** : n'importe quelle valeur dans 0 à N segments (seulement en dernier segment)</li>
     *      </ul>
     *
     * <h2>Authorization:</h2>
     * <ul>
     *     <li>permitAll():          tout le monde passe</li>
     *     <li>denyAll():            personne ne passe</li>
     *     <li>authenticated():      les users authentifiés</li>
     *     <li>anonymous():          les users non authentifiés</li>
     *     <li>hasAuthority(?)</li>
     *     <li>hasAnyAuthority(...?)</li>
     *     <li>hasRole(?)</li>
     *     <li>hasAnyRole(...?)</li>
     * </ul>
     *
     * <p>
     *     Une Authority c'est un droit sous forme de String (plus un droit à une action)<br/>
     *     Un Role est une Authority qui commence par 'ROLE_' (qui est l'utilisateur pour mon app)
     * </p>
     *
     * <dl>
     *     <dt>auth: 'ROLE_TRUC' -></dt>
     *     <dd>role: 'TRUC'</dd>
     *     <dt>auth: 'MACHIN' -></dt>
     *     <dd>(/) role</dd>
     * </dl>
     *
     * @param http the builder for the SecurityFilterChain
     * @return a configured SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter filter) throws Exception {

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore( filter, UsernamePasswordAuthenticationFilter.class);

//        http.httpBasic(); // inutile car sécurité via JWT

        http.authorizeHttpRequests( registry -> {
//            registry.requestMatchers(request -> request.getRequestURI().length() > 500).denyAll()
            registry
                    .requestMatchers(HttpMethod.GET, "/rooms/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/booking/**").authenticated()
                    .requestMatchers(HttpMethod.PATCH, "/booking/{id:[0-9]+}/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .anyRequest().hasAnyRole("AUTRE", "ADMIN");
        });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
