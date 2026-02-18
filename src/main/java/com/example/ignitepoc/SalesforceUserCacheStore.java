package com.example.ignitepoc;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;

import org.apache.ignite.cache.store.CacheStoreAdapter;

public final class SalesforceUserCacheStore extends CacheStoreAdapter<String, SalesforceUserDetails> {
    private final SalesforceClient salesforceClient;

    public SalesforceUserCacheStore() {
        this(new HttpSalesforceClient(SalesforceConfig.fromEnv()));
    }

    public SalesforceUserCacheStore(SalesforceClient salesforceClient) {
        this.salesforceClient = salesforceClient;
    }

    @Override
    public SalesforceUserDetails load(String key) throws CacheLoaderException {
        try {
            System.out.println(">> Loading user details for userId: " + key);
            return salesforceClient.getUserById(key);
        } catch (RuntimeException ex) {
            throw new CacheLoaderException("Failed to load user from Salesforce", ex);
        }
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends SalesforceUserDetails> entry) {
    }

    @Override
    public void delete(Object key) {
    }
}
