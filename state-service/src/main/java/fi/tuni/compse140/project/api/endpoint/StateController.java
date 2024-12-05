package fi.tuni.compse140.project.api.endpoint;


import fi.tuni.compse140.project.api.dto.ErrorDTO;
import fi.tuni.compse140.project.api.dto.StateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fi.tuni.compse140.project.constants.HTTPConstants.*;

@Tag(name = "State", description = "Controller for state information")
@RequestMapping(value = "/")
public interface StateController {
    String GET_STATE = "Returns system state";
    String PUT_STATE = "Updates system state, if the state transition is valid";
    String POST_STATE_RUNNING = "Sets state from INIT to RUNNING";

    @GetMapping("state")
    @Operation(summary = GET_STATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = OK,
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            ),
            @ApiResponse(
                    responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    ResponseEntity<String> getState();

    @PutMapping("state")
    @Operation(summary = PUT_STATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = NO_CONTENT
            ),
            @ApiResponse(
                    responseCode = CONFLICT,
                    description = CONFLICT_DESCRIPTION
            ),
            @ApiResponse(
                    responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    Response putState(@RequestBody StateDTO stateDTO);


    @PostMapping("state/running")
    @Operation(summary = POST_STATE_RUNNING)
    @ApiResponses({
            @ApiResponse(
                    responseCode = NO_CONTENT
            ),
            @ApiResponse(
                    responseCode = CONFLICT,
                    description = CONFLICT_DESCRIPTION
            ),
            @ApiResponse(
                    responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    Response postStateRunning();
}
