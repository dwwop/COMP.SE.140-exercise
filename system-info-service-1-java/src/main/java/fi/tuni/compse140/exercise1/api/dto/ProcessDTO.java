package fi.tuni.compse140.exercise1.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder(builderClassName = "Builder")
@Jacksonized
public record ProcessDTO(
        @Schema(description = "ID of the process", example = "2")
        Long pid,
        @Schema(description = "Terminal associated with the process", example = "root")
        String user,
        @Schema(description = "Minutes and seconds the process has been running", example = "01:13")
        String time,
        @Schema(description = "Command that started the process", example = "whoami")
        String command) {
}
