package com.rocket.course.gestao_vagas.modules.company.useCases;

import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {


    private final JobRepository repository;
    public CreateJobUseCase(JobRepository repository) {
        this.repository = repository;
    }

    public JobEntity execute(JobEntity job) {
        return this.repository.save(job);
    }
}
