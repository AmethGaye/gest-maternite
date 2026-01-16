package com.example.SIM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // autoriser H2 console
                        .anyRequest().authenticated()                 // le reste protégé
                )
                .csrf(csrf -> csrf.disable())                     // désactiver CSRF pour H2
                .headers(headers -> headers.frameOptions(frame->frame.disable())); // autoriser iframe H2

        return http.build();
    }
}
