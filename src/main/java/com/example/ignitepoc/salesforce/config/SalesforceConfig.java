package com.example.ignitepoc.salesforce.config;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public final class SalesforceConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String baseUrl;
    private final String apiVersion;
    private final String oauthToken;
    private final String serviceToken;
    private final boolean mockMode;
    private final String userId;

    private SalesforceConfig(
        String baseUrl,
        String apiVersion,
        String oauthToken,
        String serviceToken,
        boolean mockMode,
        String userId
    ) {
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
        this.oauthToken = oauthToken;
        this.serviceToken = serviceToken;
        this.mockMode = mockMode;
        this.userId = userId;
    }

    public static SalesforceConfig fromEnv() {
        String baseUrl = envOrEmpty("SF_BASE_URL");
        String apiVersion = Optional.ofNullable(System.getenv("SF_API_VERSION")).orElse("v59.0");
        String legacyAuthToken = envOrEmpty("SF_AUTH_TOKEN");
        String oauthToken = envOrEmpty("SF_OAUTH_TOKEN");
        if (oauthToken.isBlank()) {
            oauthToken = legacyAuthToken;
        }
        String serviceToken = envOrEmpty("SF_SERVICE_TOKEN");
        boolean mockMode = Optional.ofNullable(System.getenv("SF_MOCK_MODE"))
            .map(v -> !"false".equalsIgnoreCase(v.trim()))
            .orElse(true);
        String userId = Optional.ofNullable(System.getenv("SF_USER_ID")).orElse("005000000000000");
        return new SalesforceConfig(baseUrl, apiVersion, oauthToken, serviceToken, mockMode, userId);
    }

    public boolean isConfigured() {
        return !baseUrl.isBlank() && !oauthToken.isBlank() && !serviceToken.isBlank();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public boolean isMockMode() {
        return mockMode;
    }

    public String getUserId() {
        return userId;
    }

    private static String envOrEmpty(String key) {
        String value = System.getenv(key);
        return value == null ? "" : value.trim();
    }
}
