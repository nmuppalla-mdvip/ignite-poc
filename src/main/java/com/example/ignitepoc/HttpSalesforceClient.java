package com.example.ignitepoc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class HttpSalesforceClient implements SalesforceClient {
    private final SalesforceConfig config;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HttpSalesforceClient(SalesforceConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public SalesforceUserDetails getUserById(String userId) {
        if (!config.isConfigured()) {
            return new SalesforceUserDetails(userId, "Mock User", "mock.user@example.com");
        }

        String url = config.getBaseUrl()
            + "/services/data/"
            + config.getApiVersion()
            + "/sobjects/User/"
            + userId
            + "?fields=Name,Email";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + config.getAuthToken())
            .header("Accept", "application/json")
            .timeout(Duration.ofSeconds(20))
            .GET()
            .build();

        // try {
            // HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // if (response.statusCode() < 200 || response.statusCode() >= 300) {
            //     throw new IllegalStateException("Salesforce request failed: " + response.statusCode());
            // }

            // Map<String, Object> payload = objectMapper.readValue(
            //     response.body(), new TypeReference<Map<String, Object>>() {});
            // String name = stringValue(payload.get("Name"));
            // String email = stringValue(payload.get("Email"));
            // return new SalesforceUserDetails(userId, "Mock User", "mock.user@example.com");
        // } catch (InterruptedException ex) {
        //     Thread.currentThread().interrupt();
        //     throw new IllegalStateException("Salesforce request interrupted", ex);
        // } catch (IOException ex) {
        //     throw new IllegalStateException("Salesforce request failed", ex);
        // }
        return new SalesforceUserDetails(userId, "Mock User", "mock.user@example.com");
    }

    private static String stringValue(Object value) {
        return value == null ? "" : value.toString();
    }
}
