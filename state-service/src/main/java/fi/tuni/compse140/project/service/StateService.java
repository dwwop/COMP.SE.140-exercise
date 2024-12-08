package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.model.State;

import java.util.List;

public interface StateService {
    State getState();

    void setState(State state);

    void setStateRunning();

    List<String> getRunLog();

    int getRequestCountAPI();

    int getRequestCountBrowser();

    void updateRequestCountAPI();

    void updateRequestCountBrowser();

    String request();
}
