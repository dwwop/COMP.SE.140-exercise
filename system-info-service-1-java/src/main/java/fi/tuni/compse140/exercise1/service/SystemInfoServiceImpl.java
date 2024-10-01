package fi.tuni.compse140.exercise1.service;

import fi.tuni.compse140.exercise1.constants.ExceptionConstants;
import fi.tuni.compse140.exercise1.exception.InternalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class SystemInfoServiceImpl implements SystemInfoService {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String getIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception e) {
            LOG.error(ExceptionConstants.IP_EXCEPTION, e);
            throw new InternalException(ExceptionConstants.IP_EXCEPTION);
        }
    }

    @Override
    public List<String[]> getRunningProcesses() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ps", "-a");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.lines().skip(1)
                    .map(x -> x.trim().split("\\s+", 4))
                    .toList();
        } catch (IOException e) {
            LOG.error(ExceptionConstants.PROCESSES_EXCEPTION, e);
            throw new InternalException(ExceptionConstants.PROCESSES_EXCEPTION);
        }
    }

    @Override
    public Long getDiskSpace() {
        try {
            FileStore store = Files.getFileStore(Path.of("/"));
            long bytes = store.getUsableSpace();
            return DataSize.ofBytes(bytes).toMegabytes();
        } catch (Exception e) {
            LOG.error(ExceptionConstants.DISK_SPACE_EXCEPTION, e);
            throw new InternalException(ExceptionConstants.DISK_SPACE_EXCEPTION);
        }
    }

    @Override
    public Double getUptime() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cat", "/proc/uptime");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                String[] uptimeInfo = line.split(" ");
                return Double.valueOf(uptimeInfo[0]);
            }
            throw new InternalException(ExceptionConstants.UP_TIME_EXCEPTION);
        } catch (Exception e) {
            LOG.error(ExceptionConstants.UP_TIME_EXCEPTION, e);
            throw new InternalException(ExceptionConstants.UP_TIME_EXCEPTION);
        }
    }
}
