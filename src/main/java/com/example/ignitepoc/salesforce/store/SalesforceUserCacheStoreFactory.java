package com.example.ignitepoc.salesforce.store;

import java.io.Serializable;

import javax.cache.configuration.Factory;

public final class SalesforceUserCacheStoreFactory implements Factory<SalesforceUserCacheStore>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public SalesforceUserCacheStore create() {
        return new SalesforceUserCacheStore();
    }
}
