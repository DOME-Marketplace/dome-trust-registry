package com.digitelts.dome.trust.registry.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class EntitySummary {
    private String did;
    private String href;

    public EntitySummary(String did, String href) {
        this.did = did;
        this.href = href;
    }

    @Schema(name = "did", description = "The Decentralized Identifier (DID).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Schema(name = "href", description = "The link to the details of the entity.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntitySummary that = (EntitySummary) o;
        return Objects.equals(did, that.did) &&
                Objects.equals(href, that.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, href);
    }

    @Override
    public String toString() {
        return "EntitySummary{" +
                "did='" + did + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
