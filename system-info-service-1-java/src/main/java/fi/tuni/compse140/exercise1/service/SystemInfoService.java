package fi.tuni.compse140.exercise1.service;

import java.util.List;

public interface SystemInfoService {
    String getIpAddress();

    List<String[]> getRunningProcesses();

    Long getDiskSpace();

    Double getUptime();
}
