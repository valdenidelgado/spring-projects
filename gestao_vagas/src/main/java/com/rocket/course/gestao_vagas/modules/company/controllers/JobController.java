package com.rocket.course.gestao_vagas.modules.company.controllers;

import com.rocket.course.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import com.rocket.course.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    private final CreateJobUseCase useCase;

    public JobController(CreateJobUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        var entity = JobEntity.builder()
                .description(job.getDescription())
                .benefits(job.getBenefits())
                .level(job.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();
        var result = this.useCase.execute(entity);
        return ResponseEntity.ok().body(result);
    }
}
