package com.example.ignitepoc.salesforce.store;

import java.io.Serializable;

import javax.cache.Cache;

import org.apache.ignite.cache.store.CacheStoreAdapter;

import com.example.ignitepoc.salesforce.client.HttpSalesforceClient;
import com.example.ignitepoc.salesforce.client.SalesforceClient;
import com.example.ignitepoc.salesforce.config.SalesforceConfig;
import com.example.ignitepoc.salesforce.model.SalesforceUserDetails;

public final class SalesforceUserCacheStore extends CacheStoreAdapter<String, SalesforceUserDetails> implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient SalesforceClient salesforceClient;

    public SalesforceUserCacheStore() {
        this.salesforceClient = null;
    }

    public SalesforceUserCacheStore(SalesforceClient salesforceClient) {
        this.salesforceClient = salesforceClient;
    }

    private SalesforceClient client() {
        if (salesforceClient == null) {
            salesforceClient = new HttpSalesforceClient(SalesforceConfig.fromEnv());
        }
        return salesforceClient;
    }

    @Override
    public SalesforceUserDetails load(String key) {
        System.out.println(">> Loading user details for userId: " + key);
        return client().getUserById(key);
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends SalesforceUserDetails> entry) {
    }

    @Override
    public void delete(Object key) {
    }
}
