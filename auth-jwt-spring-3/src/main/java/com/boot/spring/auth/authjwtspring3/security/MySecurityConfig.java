package com.boot.spring.auth.authjwtspring3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.GET, "/api/auth/auth").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/auth/free").permitAll()
                                .anyRequest().authenticated()
                ).addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);


//        return http.addFilterBefore(new MyFilter(), SecurityFilterChain.class);
        return http.build();
    }
}
