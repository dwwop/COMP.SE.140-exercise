package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.api.dto.StateDTO;
import fi.tuni.compse140.project.facade.StateFacade;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateControllerImpl implements StateController {
    StateFacade facade;

    public StateControllerImpl(StateFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<String> getState() {
        return ResponseEntity.ok(facade.getState());
    }

    @Override
    public Response putState(StateDTO dto) {
        facade.setState(dto.state());
        return Response.noContent().build();
    }

    @Override
    public Response postStateRunning() {
        facade.setStateRunning();
        return Response.noContent().build();
    }
}
