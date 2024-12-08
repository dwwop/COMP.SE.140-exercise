package fi.tuni.compse140.project.service;

import fi.tuni.compse140.project.exception.InternalException;
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

    @Override
    public String service1() {
        HttpClient client = HttpClient.newHttpClient();

        String responseBody = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://" + apiHost + "/internal_service1"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            LOG.error(String.valueOf(e));
            throw new InternalException("Error during communication with backend services");
        }

    }
}
