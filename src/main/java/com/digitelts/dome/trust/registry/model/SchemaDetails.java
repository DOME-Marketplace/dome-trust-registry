package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;

public class SchemaDetails extends TrustedRegistryDetails{

    public SchemaDetails(String id, LocalDateTime from, LocalDateTime to) {
        super(id, from, to);
    }

    @Override
    public SchemaSummary getSummary(String url) {
        return new SchemaSummary(this.did, url+this.did);
    }
    
}
