package com.project.demoproject.services;

import com.project.demoproject.model.dto.v1.security.AccountCredentialsDTO;
import com.project.demoproject.model.dto.v1.security.TokenDTO;
import com.project.demoproject.repositories.UserRepository;
import com.project.demoproject.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = repository.findByUsername(username);
            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = provider.createAccessToken(user.getUsername(), user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + "not found.");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
