package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;

public class CredentialStatusDetails extends TrustedRegistryDetails{

    public CredentialStatusDetails(String id, LocalDateTime from, LocalDateTime to) {
        super(id, from, to);
    }

    @Override
    public CredentialStatusSummary getSummary(String url) {
        return new CredentialStatusSummary(this.did, url+this.did);
    }
    
}
