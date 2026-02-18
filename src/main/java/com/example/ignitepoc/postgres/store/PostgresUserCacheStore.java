package com.example.ignitepoc.postgres.store;

import java.io.Serializable;

import javax.cache.Cache;

import org.apache.ignite.cache.store.CacheStoreAdapter;

import com.example.ignitepoc.postgres.client.MockPostgresUserClient;
import com.example.ignitepoc.postgres.client.PostgresUserClient;
import com.example.ignitepoc.postgres.config.PostgresConfig;
import com.example.ignitepoc.postgres.model.PostgresUserDetails;

public final class PostgresUserCacheStore extends CacheStoreAdapter<String, PostgresUserDetails>
    implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient PostgresUserClient postgresUserClient;

    public PostgresUserCacheStore() {
        this.postgresUserClient = null;
    }

    public PostgresUserCacheStore(PostgresUserClient postgresUserClient) {
        this.postgresUserClient = postgresUserClient;
    }

    private PostgresUserClient client() {
        if (postgresUserClient == null) {
            postgresUserClient = new MockPostgresUserClient(PostgresConfig.fromEnv());
        }
        return postgresUserClient;
    }

    @Override
    public PostgresUserDetails load(String key) {
        System.out.println(">> Loading user details from Postgres cache store for userId: " + key);
        return client().getUserById(key);
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends PostgresUserDetails> entry) {
    }

    @Override
    public void delete(Object key) {
    }
}
