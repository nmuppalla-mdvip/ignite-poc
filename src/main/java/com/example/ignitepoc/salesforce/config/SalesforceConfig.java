package com.example.ignitepoc.salesforce.config;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public final class SalesforceConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String baseUrl;
    private final String apiVersion;
    private final String authToken;
    private final String userId;

    private SalesforceConfig(String baseUrl, String apiVersion, String authToken, String userId) {
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
        this.authToken = authToken;
        this.userId = userId;
    }

    public static SalesforceConfig fromEnv() {
        String baseUrl = envOrEmpty("SF_BASE_URL");
        String apiVersion = Optional.ofNullable(System.getenv("SF_API_VERSION")).orElse("v59.0");
        String authToken = envOrEmpty("SF_AUTH_TOKEN");
        String userId = Optional.ofNullable(System.getenv("SF_USER_ID")).orElse("005000000000000");
        return new SalesforceConfig(baseUrl, apiVersion, authToken, userId);
    }

    public boolean isConfigured() {
        return !baseUrl.isBlank() && !authToken.isBlank();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUserId() {
        return userId;
    }

    private static String envOrEmpty(String key) {
        String value = System.getenv(key);
        return value == null ? "" : value.trim();
    }
}
