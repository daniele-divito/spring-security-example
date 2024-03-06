package secexample.config;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import secexample.db.entities.ApplicationUser;
import secexample.db.enumerations.Role;
import secexample.service.ApplicationUserService;


@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private ApplicationUserService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private ApplicationAuthenticationEntryPoint applicationAuthenticationEntryPoint;

    private ApplicationAccessDeniedHandler applicationAccessDeniedHandler;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        provider.setPostAuthenticationChecks(toCheck -> {
            ApplicationUser applicationUser = (ApplicationUser) toCheck;
            if (applicationUser.getFailedLogins() > 0) {
                log.info("resetting login attempts - {}", applicationUser);
                userDetailsService.resetLoginAttempts(applicationUser);
            }
        });
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(config ->
                        config.accessDeniedHandler(applicationAccessDeniedHandler)
                                .authenticationEntryPoint(applicationAuthenticationEntryPoint)
                )
                .headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/h2-console/**").permitAll()
                        // anyone
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()

                        //employees, hr, admins, auditors
                        .requestMatchers(HttpMethod.POST, "/api/auth/setpassword").authenticated()

                        //employees, hr, admins auditor can be external to the organization so they should not request or see holidays
                        .requestMatchers(HttpMethod.GET, "/api/employee/holidays/**")
                        .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.EMPLOYEE.name(), Role.HR.name())

                        //admin and hr only
                        .requestMatchers("/api/hr/employee/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.HR.name())
                        //admin only
                        .requestMatchers("/api/admin/**").hasAuthority(Role.ADMINISTRATOR.name())

                        //admin and auditor only
                        .requestMatchers(HttpMethod.GET, "/api/security/events/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.AUDITOR.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // no session
        return httpSecurity.build();
    }
}