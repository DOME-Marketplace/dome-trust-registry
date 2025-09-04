package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase abstracta con métodos y atributos comunes
 * @see AccessNodeDetails
 * @see IssuerDetails
 * @see ParticipantDetails
 */
public abstract class Details {

    @NonNull
    protected String did;
    @NonNull
    protected LocalDateTime validFrom;
    @NonNull
    protected LocalDateTime validTo;

    
    // BUILDERS //
    public Details(String id, LocalDateTime from, LocalDateTime to){
        this.did = id;
        this.validFrom = from;
        this.validTo = to;
    }


    // SETTERS //
    public void setDid(String id){
        this.did = id;
    }

    public void setValidFrom(LocalDateTime from){
        this.validFrom = from;
    }

    public void setValidTo(LocalDateTime to){
        this.validTo = to;
    }


    // GETTERS //
    @Schema(name = "did", description = "The Decentralized Identifier (DID) of this item.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("did")
    public String getDid() {
        return this.did;
    }

    @Schema(name = "validFrom", description = "The date from which this item is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("validFrom")
    public LocalDateTime getValidFrom() {
        return this.validFrom;
    }

    @Schema(name = "validTo", description = "The date to which this item is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("validTo")
    public LocalDateTime getValidTo() {
        return this.validTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Details)) return false;
        Details that = (Details) o;
        return Objects.equals(did, that.getDid()) &&
                Objects.equals(validFrom, that.getValidFrom()) &&
                Objects.equals(validTo, that.getValidTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.did,this.validFrom,this.validTo);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "did='" + this.did + '\'' +
                ", validFrom=" + this.validFrom +
                ", validTo=" + this.validTo +
                '}';
    }

    /**
     * Método para obtener un objeto Summary a partir de la información
     * de esta clase
     * 
     * @param url La URL base que se formateará para obtener el atributo
     * 'href' de la clase Summary
     * @return El objeto de la clase Summary que representa a esta instancia
     */
    public abstract Summary getSummary(String url);
}
