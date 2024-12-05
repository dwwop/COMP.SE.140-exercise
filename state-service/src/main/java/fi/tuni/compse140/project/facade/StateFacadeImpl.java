package fi.tuni.compse140.project.facade;

import fi.tuni.compse140.project.model.State;
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

    @Override
    public void setState(State state) {
        service.setState(state);
    }

    @Override
    public void setStateRunning() {
        service.setStateRunning();
    }

    @Override
    public String getRunLog() {
        return String.join("\n", service.getRunLog());
    }

    @Override
    public String getRequestCountAPI() {
        return String.valueOf(service.getRequestCountAPI());
    }

    @Override
    public String getRequestCountBrowser() {
        return String.valueOf(service.getRequestCountBrowser());
    }

    @Override
    public void updateRequestCountAPI() {
        service.updateRequestCountAPI();
    }

    @Override
    public void updateRequestCountBrowser() {
        service.updateRequestCountBrowser();
    }

    @Override
    public void shutdown() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        }).start();
    }
}
