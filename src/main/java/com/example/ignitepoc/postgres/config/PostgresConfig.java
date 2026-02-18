package com.example.ignitepoc.postgres.config;

public final class PostgresConfig {
    private final String host;
    private final int port;
    private final String database;
    private final String username;

    public PostgresConfig(String host, int port, String database, String username) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
    }

    public static PostgresConfig fromEnv() {
        String host = getenvOrDefault("POSTGRES_HOST", "localhost");
        int port = Integer.parseInt(getenvOrDefault("POSTGRES_PORT", "5432"));
        String database = getenvOrDefault("POSTGRES_DB", "ignite_demo");
        String username = getenvOrDefault("POSTGRES_USER", "ignite_user");
        return new PostgresConfig(host, port, database, username);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    private static String getenvOrDefault(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }
}
