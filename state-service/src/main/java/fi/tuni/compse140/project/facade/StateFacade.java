package fi.tuni.compse140.project.facade;

import fi.tuni.compse140.project.model.State;

public interface StateFacade {
    String getState();

    void setState(State state);

    void setStateRunning();
}
