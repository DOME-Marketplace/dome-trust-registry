package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;

public class ServiceDetails extends TrustedRegistryDetails{

    public ServiceDetails(String id, LocalDateTime from, LocalDateTime to) {
        super(id, from, to);
    }

    @Override
    public ServiceSummary getSummary(String url) {
        return new ServiceSummary(this.did,url+this.did);
    }    
}
