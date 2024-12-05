package fi.tuni.compse140.project.facade;

import fi.tuni.compse140.project.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateFacadeImpl implements StateFacade {
    private final StateService service;

    @Autowired
    public StateFacadeImpl(StateService service) {
        this.service = service;
    }

    @Override
    public String getState() {
        return service.getState().toString();
    }
}
