package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.rocket.course.gestao_vagas.exceptions.JobNotFound;
import com.rocket.course.gestao_vagas.exceptions.UserNotFound;
import com.rocket.course.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import com.rocket.course.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository repository;
    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job when candidate does not exist")
    public void shouldNotBeAbleToApplyForAJobWhenCandidateDoesNotExist() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFound.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job when job does not exist")
    public void shouldNotBeAbleToApplyForAJobWhenJobDoesNotExist() {
        var id = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(candidate));
        try {
            applyJobCandidateUseCase.execute(id, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFound.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply for a job")
    public void shouldBeAbleToApplyForAJob() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();
        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .id(UUID.randomUUID())
                .jobId(jobId)
                .build();

        var apJob = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(repository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(apJob);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);
        assertThat(result).hasFieldOrProperty("id");
        assertThat(result).isNotNull();
        assertNotNull(result.getId());
    }
}