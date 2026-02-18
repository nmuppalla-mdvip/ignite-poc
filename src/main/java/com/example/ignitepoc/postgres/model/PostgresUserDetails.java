package com.example.ignitepoc.postgres.model;

import java.io.Serializable;

public final class PostgresUserDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final String email;

    public PostgresUserDetails(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PostgresUserDetails{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
