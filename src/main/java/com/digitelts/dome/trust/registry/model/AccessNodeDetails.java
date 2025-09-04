package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class AccessNodeDetails extends Details {

    @NotNull
    private String name; 
    @NotNull
    private String dlt_public_key; 
    @NotNull
    private String url;

    public AccessNodeDetails(String name, String id, String publicKey, String url, LocalDateTime from, LocalDateTime to){
        super(id, from, to);
        this.name = name;
        this.dlt_public_key = publicKey;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDlt_public_key(String publicKey) {
        this.dlt_public_key = publicKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Schema(name = "name", description = "The name of the access node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Schema(name = "url", description = "The URL of the access node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }   
    
    @Schema(name = "publicKey", description = "The public key associated with the DID.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("publicKey")
    public String getDlt_public_key() {
        return dlt_public_key;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessNodeDetails)) return false;
        AccessNodeDetails that = (AccessNodeDetails) o;
        return Objects.equals(did, that.did) &&
                Objects.equals(dlt_public_key, that.dlt_public_key) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, dlt_public_key, validFrom, validTo);
    }

    @Override
    public String toString() {
        return "AccessNodeDetails{" +
                "did='" + did + '\'' +
                ", ethereumPublicKey='" + dlt_public_key + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }

    @Override
    public AccessNodeSummary getSummary(String url){
        return new AccessNodeSummary(this.did, url+this.did);
    }
}
