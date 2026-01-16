package org.maternite.sante.config;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.security.CustomUserDetailsService;
import org.maternite.sante.security.JwtAuthenticationEntryPoint;
import org.maternite.sante.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints publics
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Endpoints sécurisés par rôle
                        .requestMatchers("/admin/**").hasRole("PERSONNEL_ADMIN")
                        .requestMatchers("/auth/profile").hasAnyRole("PERSONNEL_ADMIN", "SAGE_FEMME", "GYNECOLOGUE", "INFIRMIER", "PEDIATRE")
                        .requestMatchers("/patientes/**").hasAnyRole("PERSONNEL_ADMIN", "SAGE_FEMME", "GYNECOLOGUE", "INFIRMIER", "PEDIATRE")
                        .requestMatchers("/accouchements/**").hasAnyRole("PERSONNEL_ADMIN", "SAGE_FEMME", "GYNECOLOGUE")
                        .requestMatchers("/nouveau-nes/**").hasAnyRole("PERSONNEL_ADMIN", "PEDIATRE", "SAGE_FEMME")
                        .requestMatchers("/consultations/**").hasAnyRole("PERSONNEL_ADMIN", "SAGE_FEMME", "GYNECOLOGUE", "PEDIATRE")
                        .requestMatchers("/prescriptions/**").hasAnyRole("PERSONNEL_ADMIN", "GYNECOLOGUE", "PEDIATRE")
                        .requestMatchers("/plannings/**").hasAnyRole("PERSONNEL_ADMIN", "SAGE_FEMME", "GYNECOLOGUE", "PEDIATRE", "INFIRMIER")

                        // Tous les autres endpoints nécessitent une authentification
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
