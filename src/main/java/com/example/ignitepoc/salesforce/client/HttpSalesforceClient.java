package com.example.ignitepoc.salesforce.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

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
        if (config.isMockMode()) {
            Map<String, Object> idAndNamePayload = mockFetchUserFields(userId, "Id,Name", "oauth");
            Map<String, Object> emailPayload = mockFetchUserFields(userId, "Email", "service");
            return toUserDetails(userId, idAndNamePayload, emailPayload);
        }

        if (!config.isConfigured()) {
            return new SalesforceUserDetails(userId, "Mock User", "mock.user@example.com");
        }

        Map<String, Object> idAndNamePayload = fetchUserFields(userId, "Id,Name", config.getOauthToken());
        Map<String, Object> emailPayload = fetchUserFields(userId, "Email", config.getServiceToken());

        return toUserDetails(userId, idAndNamePayload, emailPayload);
    }

    private SalesforceUserDetails toUserDetails(
        String fallbackUserId,
        Map<String, Object> idAndNamePayload,
        Map<String, Object> emailPayload
    ) {
        String resolvedId = stringValue(idAndNamePayload.get("Id"));
        if (resolvedId.isBlank()) {
            resolvedId = fallbackUserId;
        }

        String resolvedName = stringValue(idAndNamePayload.get("Name"));
        String resolvedEmail = stringValue(emailPayload.get("Email"));

        return new SalesforceUserDetails(resolvedId, resolvedName, resolvedEmail);
    }

    private Map<String, Object> mockFetchUserFields(String userId, String fields, String tokenType) {
        System.out.println(">> [MOCK Salesforce API] tokenType=" + tokenType + ", fields=" + fields + ", userId=" + userId);
        if ("Id,Name".equals(fields)) {
            return Map.of(
                "Id", userId,
                "Name", "Mock Name " + userId
            );
        }
        return Map.of("Email", "mock." + userId + "@example.com");
    }

    private Map<String, Object> fetchUserFields(String userId, String fields, String bearerToken) {
        String url = config.getBaseUrl()
            + "/services/data/"
            + config.getApiVersion()
            + "/sobjects/User/"
            + userId
            + "?fields="
            + fields;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + bearerToken)
            .header("Accept", "application/json")
            .timeout(Duration.ofSeconds(20))
            .GET()
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException(
                    "Salesforce request failed with status " + response.statusCode() + " for fields=" + fields);
            }
            return objectMapper.readValue(response.body(), objectMapper.getTypeFactory()
                .constructMapType(Map.class, String.class, Object.class));
        }
        catch (Exception ex) {
            throw new IllegalStateException("Failed to load Salesforce user fields: " + fields, ex);
        }
    }

    private static String stringValue(Object value) {
        return value == null ? "" : value.toString();
    }
}
