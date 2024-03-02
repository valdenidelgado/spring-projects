package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    private final JobRepository repository;

    public ListAllJobsByFilterUseCase(JobRepository repository) {
        this.repository = repository;
    }

    public List<JobEntity> execute(String filter) {
        return this.repository.findByDescriptionContainingIgnoreCase(filter);
    }
}
