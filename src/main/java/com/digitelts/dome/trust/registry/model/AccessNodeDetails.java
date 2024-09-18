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
    private String did; 
    @NotNull
    private String publicKey; 
    @NotNull
    private LocalDateTime validFrom; 
    @NotNull
    private LocalDateTime validTo;

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
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
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
                Objects.equals(publicKey, that.publicKey) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, publicKey, validFrom, validTo);
    }

    @Override
    public String toString() {
        return "AccessNodeDetails{" +
                "did='" + did + '\'' +
                ", ethereumPublicKey='" + publicKey + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }
}
