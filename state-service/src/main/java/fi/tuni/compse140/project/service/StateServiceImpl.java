package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.model.State;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {
    private static final State state = State.INIT;

    @Override
    public State getState() {
        return state;
    }
}
