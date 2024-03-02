package com.rocket.course.gestao_vagas.modules.candidate.controllers;

import com.rocket.course.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private final AuthCandidateUseCase useCase;

    public AuthCandidateController(AuthCandidateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateDTO dto) {
        try {
            var result = this.useCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
