package fi.tuni.compse140.exercise1.facade;

import fi.tuni.compse140.exercise1.api.dto.ProcessDTO;
import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;
import fi.tuni.compse140.exercise1.service.SystemInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemInfoFacadeImpl implements SystemInfoFacade {
    SystemInfoService systemInfoService;

    public SystemInfoFacadeImpl(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @Override
    public List<SystemInfoDTO> getSystemInfo() {
        String ipAddress = systemInfoService.getIpAddress();
        List<ProcessDTO> runningProcesses = systemInfoService.getRunningProcesses().stream()
                .map(x -> new ProcessDTO(Long.valueOf(x[0]), x[1], x[2], x[3]))
                .toList();
        Long availableSpace = systemInfoService.getDiskSpace();
        Double upTime = systemInfoService.getUptime();

        SystemInfoDTO systemInfo = new SystemInfoDTO(ipAddress, runningProcesses, availableSpace, upTime);
        return List.of(systemInfo);
    }
}
