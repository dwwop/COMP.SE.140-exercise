package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.api.dto.StateDTO;
import fi.tuni.compse140.project.facade.StateFacade;
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
    public ResponseEntity<Void> putState(StateDTO dto) {
        facade.setState(dto.state());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> postStateRunning() {
        facade.setStateRunning();
        return ResponseEntity.noContent().build();
    }
}
