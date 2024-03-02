package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository repository;

    private final PasswordEncoder encoder;

    public CreateCandidateUseCase(CandidateRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public CandidateEntity execute(CandidateEntity candidate) {
        this.repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(c -> {
                    throw new UserFoundException();
                });

        candidate.setPassword(this.encoder.encode(candidate.getPassword()));

        return this.repository.save(candidate);
    }
}
