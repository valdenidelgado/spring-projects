package com.rocket.course.gestao_vagas.modules.company.repositories;

import com.rocket.course.gestao_vagas.modules.company.entities.CompanyEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
