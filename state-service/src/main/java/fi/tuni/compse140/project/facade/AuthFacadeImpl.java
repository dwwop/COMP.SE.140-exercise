package fi.tuni.compse140.project.facade;

import fi.tuni.compse140.project.api.dto.AuthDTO;
import fi.tuni.compse140.project.api.dto.AuthRespDTO;
import fi.tuni.compse140.project.service.AuthService;
import fi.tuni.compse140.project.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthFacadeImpl implements AuthFacade {
    AuthService authService;
    StateService stateService;

    @Autowired
    public AuthFacadeImpl(AuthService authService, StateService stateService) {
        this.authService = authService;
        this.stateService = stateService;
    }


    @Override
    public AuthRespDTO verify(AuthDTO authDTO) {
        boolean verified = authService.verify(authDTO.username(), authDTO.password());
        if (Boolean.TRUE.equals(verified)) {
            stateService.setStateRunning();
        }
        return new AuthRespDTO(verified);
    }
}
