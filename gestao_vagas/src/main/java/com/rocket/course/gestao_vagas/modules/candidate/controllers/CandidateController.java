package com.rocket.course.gestao_vagas.modules.candidate.controllers;

import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase useCase;

    public CandidateController(CreateCandidateUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public String getCandidate() {
        return "Candidate";
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.useCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
