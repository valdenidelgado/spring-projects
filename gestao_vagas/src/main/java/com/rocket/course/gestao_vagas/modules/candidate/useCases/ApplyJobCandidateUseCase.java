package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.exceptions.JobNotFound;
import com.rocket.course.gestao_vagas.exceptions.UserNotFound;
import com.rocket.course.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.rocket.course.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    private final CandidateRepository repository;

    private final JobRepository jobRepository;

    private final ApplyJobRepository applyJobRepository;

    public ApplyJobCandidateUseCase(CandidateRepository repository, JobRepository jobRepository, ApplyJobRepository applyJobRepository) {
        this.repository = repository;
        this.jobRepository = jobRepository;
        this.applyJobRepository = applyJobRepository;
    }

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        var candidate = this.repository.findById(candidateId)
                .orElseThrow(UserNotFound::new);
        var job = this.repository.findById(jobId)
                .orElseThrow(JobNotFound::new);

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        return applyJobRepository.save(applyJob);
    }
}
