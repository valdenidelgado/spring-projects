package com.rocket.course.gestao_vagas.modules.company.useCases;

import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.company.entities.CompanyEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public CompanyEntity execute(CompanyEntity company) {
        this.repository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent(c -> {
                    throw new UserFoundException();
                });
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        return this.repository.save(company);
    }
}
