package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.modules.candidate.dto.ProfileResponse;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileUseCase {

    private final CandidateRepository repository;

    public ProfileUseCase(CandidateRepository repository) {
        this.repository = repository;
    }

    public ProfileResponse execute(UUID candidateId) {
        var entity = this.repository.findById(candidateId)
                .orElseThrow(() -> new UsernameNotFoundException("Candidate not found."));
        return ProfileResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .description(entity.getDescription())
                .build();
    }
}
