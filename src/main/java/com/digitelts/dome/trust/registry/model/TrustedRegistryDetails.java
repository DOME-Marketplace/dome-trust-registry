package com.digitelts.dome.trust.registry.model;


import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Abstract class with common methods and attributes
 * @see AccessNodeDetails
 * @see LEARCredentialIssuerDetails
 * @see ParticipantDetails
 * @see CredentialStatusDetails
 * @see SchemaDetails
 * @see ServiceDetails
 */
public abstract class TrustedRegistryDetails {

    @NonNull
    protected String id;

    
    // BUILDERS //
    public TrustedRegistryDetails(String id){
        this.id = id;
    }


    // SETTERS //
    public void setId(String id){
        this.id = id;
    }

    // GETTERS //
    @Schema(name = "did", description = "The identifier of this item.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("did")
    public String getId() {
        return this.id;
    }

    /**
     * Method to obtain a {@code Summary} class object from the current class information
     * 
     * @param url The base URL which will be formatted to get the {@code href} attribute
     * {@code href} of the {@code Summary} class
     * @return The {@code Summary} class object representing this instance
     */
    public abstract TrustedRegistrySummary getSummary(String url);
}
