package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.exception.InvalidTransitionException;
import fi.tuni.compse140.project.model.State;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class StateServiceImpl implements StateService {
    private static final Map<State, Set<State>> validTransitions = Map.of(
            State.INIT, Set.of(),
            State.RUNNING, Set.of(State.INIT, State.PAUSED, State.SHUTDOWN),
            State.PAUSED, Set.of(State.INIT, State.RUNNING, State.SHUTDOWN),
            State.SHUTDOWN, Set.of()
    );
    private static State state = State.INIT;

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        if (validTransitions.get(StateServiceImpl.state).contains(state)) {
            StateServiceImpl.state = state;
        } else if (!Objects.equals(StateServiceImpl.state, state)) {
            throw new InvalidTransitionException(StateServiceImpl.state, state);
        }
    }

    @Override
    public void setStateRunning() {
        if (State.INIT.equals(StateServiceImpl.state)) {
            StateServiceImpl.state = State.RUNNING;
        } else {
            throw new InvalidTransitionException(StateServiceImpl.state, State.RUNNING);
        }
    }
}
