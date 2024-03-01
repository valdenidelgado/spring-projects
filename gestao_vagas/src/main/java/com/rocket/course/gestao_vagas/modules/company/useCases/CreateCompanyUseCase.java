package com.rocket.course.gestao_vagas.modules.company.useCases;

import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.company.entities.CompanyEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository repository;

    public CreateCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyEntity execute(CompanyEntity company) {
        this.repository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent(c -> {
                    throw new UserFoundException();
                });
        return this.repository.save(company);
    }
}
