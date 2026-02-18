package com.example.ignitepoc.postgres.store;

import java.io.Serializable;

import javax.cache.configuration.Factory;

public final class PostgresUserCacheStoreFactory implements Factory<PostgresUserCacheStore>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public PostgresUserCacheStore create() {
        return new PostgresUserCacheStore();
    }
}
