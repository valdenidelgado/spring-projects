package com.rocket.course.gestao_vagas.modules.company.controllers;

import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import com.rocket.course.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase useCase;

    public JobController(CreateJobUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity job) {
        var result = this.useCase.execute(job);
        return ResponseEntity.ok().body(result);
    }
}
