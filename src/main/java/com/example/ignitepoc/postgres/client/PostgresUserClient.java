package com.example.ignitepoc.postgres.client;

import com.example.ignitepoc.postgres.model.PostgresUserDetails;

public interface PostgresUserClient {
    PostgresUserDetails getUserById(String userId);
}
