package com.example.ignitepoc.salesforce.model;

import java.io.Serializable;

public final class SalesforceUserDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final String email;

    public SalesforceUserDetails(String id, String name, String email) {
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
        return "SalesforceUserDetails{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
