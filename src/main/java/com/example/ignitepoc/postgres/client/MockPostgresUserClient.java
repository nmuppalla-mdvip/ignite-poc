package com.example.ignitepoc.postgres.client;

import java.util.HashMap;
import java.util.Map;

import com.example.ignitepoc.postgres.config.PostgresConfig;
import com.example.ignitepoc.postgres.model.PostgresUserDetails;

public final class MockPostgresUserClient implements PostgresUserClient {
    private final Map<String, PostgresUserDetails> usersById = new HashMap<>();

    public MockPostgresUserClient(PostgresConfig config) {
        usersById.put("pg-100", new PostgresUserDetails("pg-100", "Postgres Demo User", "pg100@example.com"));
        usersById.put("pg-101", new PostgresUserDetails("pg-101", "Data Engineer", "data.engineer@example.com"));
        usersById.put(
            "pg-admin",
            new PostgresUserDetails("pg-admin", "Platform Admin", config.getUsername() + "@" + config.getHost())
        );
    }

    @Override
    public PostgresUserDetails getUserById(String userId) {
        PostgresUserDetails found = usersById.get(userId);
        if (found != null) {
            return found;
        }
        return new PostgresUserDetails(userId, "Mock User " + userId, userId + "@demo.local");
    }
}
