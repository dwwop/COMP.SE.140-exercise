package fi.tuni.compse140.project.facade;

import fi.tuni.compse140.project.api.dto.AuthDTO;
import fi.tuni.compse140.project.api.dto.AuthRespDTO;

public interface AuthFacade {
    AuthRespDTO verify(AuthDTO authDTO);
}
