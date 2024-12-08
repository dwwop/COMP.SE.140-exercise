package fi.tuni.compse140.project.api.endpoint;

import fi.tuni.compse140.project.facade.StateFacade;
import fi.tuni.compse140.project.model.State;
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
    public ResponseEntity<Void> putState(State state) {
        facade.setState(state);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> postStateRunning() {
        facade.setStateRunning();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<String> getRunLog() {
        return ResponseEntity.ok(facade.getRunLog());
    }

    @Override
    public ResponseEntity<String> getRequestCountApi() {
        return ResponseEntity.ok(facade.getRequestCountAPI());
    }

    @Override
    public ResponseEntity<String> getRequestCountBrowser() {
        return ResponseEntity.ok(facade.getRequestCountBrowser());
    }

    @Override
    public ResponseEntity<Void> putRequestCountApi() {
        facade.updateRequestCountAPI();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> putRequestCountBrowser() {
        facade.updateRequestCountBrowser();
        return ResponseEntity.noContent().build();
    }
    @Override
    public ResponseEntity<Void> shutdown() {
        facade.shutdown();
        return ResponseEntity.noContent().build();
    }
}
