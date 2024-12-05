package fi.tuni.compse140.project.api.dto;

import fi.tuni.compse140.project.model.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder(builderClassName = "Builder")
@Jacksonized
public record StateDTO(
        @Schema(description = "State")
        State state) {
}
