package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;
import java.time.LocalDateTime;

public class AccessNodeDetails {

    @NotNull
    private String name; 
    @NotNull
    private String did; 
    @NotNull
    private String dlt_public_key; 
    @NotNull
    private String url; 
    @NotNull
    private LocalDateTime validFrom; 
    @NotNull
    private LocalDateTime validTo;

    @Schema(name = "name", description = "The name of the access node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    
    @Schema(name = "did", description = "The Decentralized Identifier (DID) of the access node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Schema(name = "publicKey", description = "The public key associated with the DID.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("ethereumPublicKey")
    public String getDlt_public_key() {
        return dlt_public_key;
    }

    public void setDlt_public_key(String publicKey) {
        this.dlt_public_key = publicKey;
    }

    //getter and setter for the url property
    @Schema(name = "url", description = "The URL of the access node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    @Schema(name = "validFrom", description = "The date from which the access node is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("validFrom")
    public LocalDateTime getValidFrom() {
        return validFrom;
    }


    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }


    @Schema(name = "validTo", description = "The date to which the access node is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("validTo")
    public LocalDateTime getValidTo() {
        return validTo;
    }


    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
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
}
