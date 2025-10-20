package com.digitelts.dome.trust.registry.model;

import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Abstract class with common methods and attributes
 * @see AccessNodeSummary
 * @see LEARCredentialIssuerSummary
 * @see ParticipantSummary
 * @see ServiceSummary
 * @see SchemaSummary
 */
public abstract class TrustedRegistrySummary {

    @NonNull
    protected String did;
    @NonNull
    protected String href;

    public TrustedRegistrySummary(String did, String href){
        this.did = did;
        this.href = href;
    }

    public void setDid(String did){
        this.did = did;
    }

    public void setHref(String href){
        this.href = href;
    }

    @Schema(name = "href", description = "The link to the details of this item.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @Schema(name = "did", description = "The Decentralized Identifier (DID) of this item.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("did")
    public String getDid() {
        return did;
    }
}
