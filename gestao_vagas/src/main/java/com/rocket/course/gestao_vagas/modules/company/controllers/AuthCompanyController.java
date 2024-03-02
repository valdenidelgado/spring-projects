package com.rocket.course.gestao_vagas.modules.company.controllers;

import com.rocket.course.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.rocket.course.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    private final AuthCompanyUseCase useCase;

    public AuthCompanyController(AuthCompanyUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyDTO dto) {
        try {
            var result = this.useCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
