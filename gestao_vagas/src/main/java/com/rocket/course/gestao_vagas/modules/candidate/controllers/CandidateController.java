package com.rocket.course.gestao_vagas.modules.candidate.controllers;

import com.rocket.course.gestao_vagas.modules.candidate.dto.ProfileResponse;
import com.rocket.course.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.rocket.course.gestao_vagas.modules.candidate.useCases.ProfileUseCase;
import com.rocket.course.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidate API")
public class CandidateController {

    private final CreateCandidateUseCase useCase;
    private final ProfileUseCase profileUseCase;

    private final ListAllJobsByFilterUseCase listJobs;

    public CandidateController(CreateCandidateUseCase useCase, ProfileUseCase profileUseCase, ListAllJobsByFilterUseCase listJobs) {
        this.useCase = useCase;
        this.profileUseCase = profileUseCase;
        this.listJobs = listJobs;
    }

    @GetMapping
    public String getCandidate() {
        return "Candidate";
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.useCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Profile", description = "Profile of candidate")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var result = this.profileUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "List all jobs", description = "List all jobs by filter")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                    )
            })
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<JobEntity>> getJobs(@RequestParam String filter) {
        try {
            var result = this.listJobs.execute(filter);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }
}
