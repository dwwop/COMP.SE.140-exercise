package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.api.dto.AuthDTO;
import fi.tuni.compse140.project.api.dto.AuthRespDTO;
import fi.tuni.compse140.project.facade.AuthFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    AuthFacade facade;

    @Autowired
    public AuthControllerImpl(AuthFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<AuthRespDTO> verify(AuthDTO authDTO) {
        AuthRespDTO authRespDTO = facade.verify(authDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, "authenticated=" + authRespDTO.authenticated() + "; HttpOnly; Path=/");

        return ResponseEntity.ok()
                .headers(headers)
                .body(authRespDTO);
    }
}
