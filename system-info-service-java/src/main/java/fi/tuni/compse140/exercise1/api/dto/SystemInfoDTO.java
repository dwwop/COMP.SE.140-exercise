package fi.tuni.compse140.exercise1.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder(builderClassName = "Builder")
@Jacksonized
public record SystemInfoDTO(
        @Schema(description = "IP Adress of container", example = "207.11.119.205")
        String ipAddress,
        @Schema(description = "List of running processes")
        List<ProcessDTO> processes,
        @Schema(description = "Available disk space in MB", example = "12")
        Long diskSpace,
        @Schema(description = "Time since last boot in seconds", example = "5541.3")
        Double lastBootTime) {
}
