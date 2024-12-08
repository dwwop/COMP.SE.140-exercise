package fi.tuni.compse140.project.facade;

public interface StateFacade {
    String getState();

    void setState(String state);

    void setStateRunning();

    String getRunLog();

    String getRequestCountAPI();

    String getRequestCountBrowser();

    void updateRequestCountAPI();

    void updateRequestCountBrowser();

    void shutdown();

    String getRequest();
}
