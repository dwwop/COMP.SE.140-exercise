package fi.tuni.compse140.project.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder(builderClassName = "Builder")
@Jacksonized
public record AuthRespDTO(@Schema(description = "Information about authentication") boolean authenticated) {
}