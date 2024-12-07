package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.api.dto.AuthDTO;
import fi.tuni.compse140.project.api.dto.AuthRespDTO;
import fi.tuni.compse140.project.facade.AuthFacade;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(facade.verify(authDTO));
    }
}
