package fi.tuni.compse140.exercise1.facade;

import fi.tuni.compse140.exercise1.api.dto.ProcessDTO;
import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;
import fi.tuni.compse140.exercise1.service.DotNetSystemInfoService;
import fi.tuni.compse140.exercise1.service.SystemInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemInfoFacadeImpl implements SystemInfoFacade {
    SystemInfoService systemInfoService;
    DotNetSystemInfoService dotNetSystemInfoService;


    public SystemInfoFacadeImpl(SystemInfoService systemInfoService, DotNetSystemInfoService dotNetSystemInfoService) {
        this.systemInfoService = systemInfoService;
        this.dotNetSystemInfoService = dotNetSystemInfoService;
    }

    private SystemInfoDTO getInternalSystemInfo() {
        String ipAddress = systemInfoService.getIpAddress();
        List<ProcessDTO> runningProcesses = systemInfoService.getRunningProcesses().stream()
                .map(x -> new ProcessDTO(Long.valueOf(x[0]), x[1], x[2], x[3]))
                .toList();
        Long availableSpace = systemInfoService.getDiskSpace();
        Double upTime = systemInfoService.getUptime();

        return new SystemInfoDTO(ipAddress, runningProcesses, availableSpace, upTime);
    }

    @Override
    public List<SystemInfoDTO> getSystemInfo() {
        return List.of(getInternalSystemInfo(), dotNetSystemInfoService.getSystemInfo());
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
