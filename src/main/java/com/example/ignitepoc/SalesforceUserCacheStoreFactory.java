package com.example.ignitepoc;
import javax.cache.configuration.Factory;

public class SalesforceUserCacheStoreFactory
        implements Factory<SalesforceUserCacheStore> {

    private static final long serialVersionUID = 1L;

    @Override
    public SalesforceUserCacheStore create() {
        return new SalesforceUserCacheStore();
    }
}
