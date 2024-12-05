package fi.tuni.compse140.project.api.endpoint;

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
}
