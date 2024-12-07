package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.api.dto.AuthDTO;
import fi.tuni.compse140.project.api.dto.AuthRespDTO;
import fi.tuni.compse140.project.api.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static fi.tuni.compse140.project.constants.HTTPConstants.*;

@Tag(name = "Auth", description = "Controller for authentication")
@RequestMapping(value = "/auth")
public interface AuthController {
    String POST_VERIFY = "Verifies username and password and changes system state to INIT upon success";

    @PostMapping("/verify")
    @Operation(summary = POST_VERIFY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = OK,
                    content = @Content(schema = @Schema(implementation = AuthRespDTO.class))
            ),
            @ApiResponse(
                    responseCode = SERVER_ERROR,
                    description = SERVER_ERROR_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
    })
    ResponseEntity<AuthRespDTO> verify(@RequestBody AuthDTO authDTO);
}
