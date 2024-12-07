package fi.tuni.compse140.project.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder(builderClassName = "Builder")
@Jacksonized
public record ErrorDTO(
        @Schema(description = "Error message", example = "Unable to verify credentials")
        String errorMessage) {
}
