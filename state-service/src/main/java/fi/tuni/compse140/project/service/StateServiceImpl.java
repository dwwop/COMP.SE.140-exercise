package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.exception.InvalidTransitionException;
import fi.tuni.compse140.project.model.State;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class StateServiceImpl implements StateService {
    private static final Map<State, Set<State>> validTransitions = Map.of(
            State.INIT, Set.of(),
            State.RUNNING, Set.of(State.INIT, State.PAUSED, State.SHUTDOWN),
            State.PAUSED, Set.of(State.INIT, State.RUNNING, State.SHUTDOWN),
            State.SHUTDOWN, Set.of()
    );
    private static State state = State.INIT;
    private static final List<String> runLog = new ArrayList<>();
    private static int requestCountApi = 0;
    private static  int requestCountBrowser = 0;

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        if (validTransitions.get(StateServiceImpl.state).contains(state)) {
            addToRunLog(StateServiceImpl.state, state);
            StateServiceImpl.state = state;
        } else if (!Objects.equals(StateServiceImpl.state, state)) {
            throw new InvalidTransitionException(StateServiceImpl.state, state);
        }
    }

    private void addToRunLog(State from, State to) {
        runLog.add(String.format("%s: %s->%s", Instant.now(), from, to));
    }

    @Override
    public void setStateRunning() {
        if (State.INIT.equals(StateServiceImpl.state)) {
            addToRunLog(State.INIT, State.RUNNING);
            StateServiceImpl.state = State.RUNNING;
        } else {
            throw new InvalidTransitionException(StateServiceImpl.state, State.RUNNING);
        }
    }

    @Override
    public List<String> getRunLog() {
        return runLog;
    }

    @Override
    public int getRequestCountAPI() {
        return requestCountApi;
    }

    @Override
    public int getRequestCountBrowser() {
        return requestCountBrowser;
    }

    @Override
    public void updateRequestCountAPI() {
        requestCountApi += 1;
    }

    @Override
    public void updateRequestCountBrowser() {
        requestCountBrowser += 1;
    }
}
