package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CredentialStatusDetails extends TrustedRegistryDetails{

    private boolean isValid;
    private boolean isRevoked;

    public CredentialStatusDetails(String id, LocalDateTime from, LocalDateTime to, boolean valid, boolean revoked) {
        super(id, from, to);
        this.isValid = valid;
        this.isRevoked = revoked;
    }

    public void setValidity(boolean valid){
        this.isValid = valid;
    }

    public void setRevocation(boolean revoked){
        this.isRevoked = revoked;
    }

    @Schema(name = "is_valid", description = "Whether the certificate is valid or not", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("is_valid")
    public boolean getValidity(){
        return this.isValid;
    }

    @Schema(name = "is_revoked", description = "Whether the certificate is revoked or not", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("is_revoked")
    public boolean getRevocation(){
        return this.isRevoked;
    }

    @Override
    public CredentialStatusSummary getSummary(String url) {
        return new CredentialStatusSummary(this.did, url+this.did);
    }
    
}
