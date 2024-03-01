package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository repository;

    public CreateCandidateUseCase(CandidateRepository repository) {
        this.repository = repository;
    }

    public CandidateEntity execute(CandidateEntity candidate) {
        this.repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(c -> {
                    throw new UserFoundException();
                });
        return this.repository.save(candidate);
    }
}
