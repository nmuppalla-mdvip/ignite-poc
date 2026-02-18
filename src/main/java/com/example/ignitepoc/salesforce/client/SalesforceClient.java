package com.example.ignitepoc.salesforce.client;

import com.example.ignitepoc.salesforce.model.SalesforceUserDetails;

public interface SalesforceClient {
    SalesforceUserDetails getUserById(String userId);
}
