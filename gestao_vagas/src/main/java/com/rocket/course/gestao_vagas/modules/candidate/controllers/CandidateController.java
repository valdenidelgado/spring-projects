package com.rocket.course.gestao_vagas.modules.candidate.controllers;

import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.ProfileUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase useCase;
    private final ProfileUseCase profileUseCase;

    public CandidateController(CreateCandidateUseCase useCase, ProfileUseCase profileUseCase) {
        this.useCase = useCase;
        this.profileUseCase = profileUseCase;
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

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var result = this.profileUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
