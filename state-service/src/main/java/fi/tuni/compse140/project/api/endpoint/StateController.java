package fi.tuni.compse140.project.api.endpoint;


import fi.tuni.compse140.project.api.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static fi.tuni.compse140.project.constants.HTTPConstants.*;

@Tag(name = "State", description = "Controller for state information")
@RequestMapping(value = "/")
public interface StateController {
    String GET_STATE = "Returns system state";

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
}
