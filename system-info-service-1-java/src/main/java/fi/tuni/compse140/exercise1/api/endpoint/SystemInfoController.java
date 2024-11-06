package fi.tuni.compse140.exercise1.api.endpoint;


import fi.tuni.compse140.exercise1.api.dto.ErrorDTO;
import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static fi.tuni.compse140.exercise1.constants.HTTPConstants.*;

@Tag(name = "SystemInfo", description = "Controller for system information")
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SystemInfoController {
    String GET_SYSTEM_INFO = "Get information about system including IP address, running processes, disk space and time since last boot. Include the same information from service 2";
    String SHUTDOWN = "Shutdowns service 2s after receiving request";

    @GetMapping
    @Operation(summary = GET_SYSTEM_INFO)
    @ApiResponses({
            @ApiResponse(
                    responseCode = OK
            ),
            @ApiResponse(
                    responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    ResponseEntity<List<SystemInfoDTO>> getInformation();

    @PostMapping("shutdown")
    @Operation(summary = SHUTDOWN)
    @ApiResponses({
            @ApiResponse(
                    responseCode = NO_CONTENT
            ),
            @ApiResponse(responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    Response shutdown();
}
