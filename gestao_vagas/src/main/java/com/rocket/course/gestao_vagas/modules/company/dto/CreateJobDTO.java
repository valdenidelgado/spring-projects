package com.rocket.course.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {

    @Schema(example = "Desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Cafezin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "SENIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
