package com.example.ignitepoc.salesforce.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

import com.example.ignitepoc.salesforce.config.SalesforceConfig;
import com.example.ignitepoc.salesforce.model.SalesforceUserDetails;
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

        return new SalesforceUserDetails(userId, "Mock User", "mock.user@example.com");
    }

    private static String stringValue(Object value) {
        return value == null ? "" : value.toString();
    }
}
