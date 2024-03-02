package com.rocket.course.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Cafezin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "SENIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
