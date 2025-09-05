package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase abstracta con métodos y atributos comunes
 * @see AccessNodeSummary
 * @see IssuerSummary
 * @see ParticipantSummary
 * @see CredentialStatusSummary
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
        TrustedRegistrySummary summary = (TrustedRegistrySummary) o;
        return Objects.equals(this.did, summary.getDid()) &&
                Objects.equals(this.href, summary.getHref());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.did,this.href);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ").append(this.getClass().getSimpleName()).append(" {\n");
        sb.append("    did: ").append(toIndentedString(this.did)).append("\n");
        sb.append("    href: ").append(toIndentedString(this.href)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
        return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
