package fi.tuni.compse140.project.service;

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

@Service
public class NginxGatewayImpl implements NginxGateway {
    private static final Logger LOG = LogManager.getLogger();

    @Value("${NGINX_HOST:localhost}")
    private String apiHost;

    @Override
    public void shutdown() {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + apiHost + "/shutdown"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException | URISyntaxException e) {
            LOG.error(String.valueOf(e));
        }
    }
}
