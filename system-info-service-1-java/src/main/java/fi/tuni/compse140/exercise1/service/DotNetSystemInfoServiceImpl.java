package fi.tuni.compse140.exercise1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.compse140.exercise1.api.dto.SystemInfoDTO;
import fi.tuni.compse140.exercise1.constants.ExceptionConstants;
import fi.tuni.compse140.exercise1.exception.ExternalAPIException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class DotNetSystemInfoServiceImpl implements DotNetSystemInfoService {
    private static final Logger LOG = LogManager.getLogger();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${SERVICE_2_HOST:localhost}")
    private String apiHost;

    @Override
    public SystemInfoDTO getSystemInfo() {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + apiHost))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                List<SystemInfoDTO> dtoList = MAPPER.readValue(response.body(), new TypeReference<>() {
                });
                return dtoList.getFirst();
            } else {
                LOG.error("Exception during communication with Service 2: {}", response.body());
                throw new ExternalAPIException(ExceptionConstants.SERVICE_2_EXCEPTION);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            LOG.error("Exception during communication with Service 2", e);
            throw new ExternalAPIException(ExceptionConstants.SERVICE_2_EXCEPTION, e);
        }
    }
}
