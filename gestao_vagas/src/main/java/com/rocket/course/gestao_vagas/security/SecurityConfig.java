package com.rocket.course.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityFilter filter;

    private final SecurityCandidateFilter candidateFilter;

    public SecurityConfig(SecurityFilter filter, SecurityCandidateFilter candidateFilter) {
        this.filter = filter;
        this.candidateFilter = candidateFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/candidate/").permitAll()
                        .requestMatchers("/company/**").permitAll()
                        .requestMatchers("/company/auth/**").permitAll()
                        .requestMatchers("/candidate/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(this.candidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(this.filter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
