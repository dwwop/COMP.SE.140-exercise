package fi.tuni.compse140.exercise1.facade;

import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;

import java.util.List;

public interface SystemInfoFacade {
    List<SystemInfoDTO> getSystemInfo();

    void shutdown();
}
