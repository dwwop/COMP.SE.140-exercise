package fi.tuni.compse140.exercise1.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder(builderClassName = "Builder")
@Jacksonized
public record ErrorDTO(
        @Schema(description = "Error message", example = "Exception occurred when reading address id")
        String errorMessage) {
}
