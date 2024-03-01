package com.rocket.course.gestao_vagas.modules.candidate.repositories;

import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
