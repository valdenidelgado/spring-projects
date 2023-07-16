package com.boot.spring.auth.authjwtspring3.controllers;

import com.boot.spring.auth.authjwtspring3.domain.User;
import com.boot.spring.auth.authjwtspring3.domain.dto.AuthDTO;
import com.boot.spring.auth.authjwtspring3.domain.dto.LoginResponseDTO;
import com.boot.spring.auth.authjwtspring3.domain.dto.RegisterDTO;
import com.boot.spring.auth.authjwtspring3.infra.security.TokenService;
import com.boot.spring.auth.authjwtspring3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.manager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encodedPassword, data.role());

        this.repository.save(user);
        return ResponseEntity.ok().build();
    }
}
