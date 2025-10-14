/**
 * This file contains the classes related to the LEAR Credential Issuer field 'attributes'
 */
package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;
import java.util.*;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * Class representing an object with LEAR Credential Issuer's attributes
 */
@Schema(description = "LEAR Credential Issuer's attributes")
public class Attribute {
    private String hash;
    private String issuerType;
    private AttributeBody body;

    Attribute(String hash, String issuerType, AttributeBody body){
        this.hash = hash;
        this.issuerType = issuerType;
        this.body = body;
    }

    @Schema(name = "hash", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Schema(name = "issuerType", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("issuerType")
    public String getIssuerType() {
        return issuerType;
    }

    public void setIssuerType(String issuerType) {
        this.issuerType = issuerType;
    }

    @Schema(name = "body", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("body")
    public AttributeBody getBody() {
        return body;
    }

    public void setBody(AttributeBody body) {
        this.body = body;
    }

}

/**
 * Class representing the 'body' field of LEAR Credential Issuer's attributes
 */
class AttributeBody{
    private String credentialsType;
    private AttributeValidity validFor;
    private List<AttributeClaim> claims;

    AttributeBody(String credentialsType, AttributeValidity validFor, @Nullable List<AttributeClaim> claims){
        this.credentialsType = credentialsType;
        this.validFor = validFor;
        if(claims==null) this.claims = new ArrayList<>();
        else this.claims = claims;
    }

    @Schema(name = "credentialsType", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("credentialsType")
    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    @Schema(name = "validFor", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("validFor")
    public AttributeValidity getValidFor() {
        return validFor;
    }

    public void setValidFor(AttributeValidity validFor) {
        this.validFor = validFor;
    }

    @Schema(name = "claims", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("claims")
    public List<AttributeClaim> getClaims() {
        return claims;
    }

    public void setClaims(List<AttributeClaim> claims) {
        this.claims = claims;
    }

}

/**
 * Class representing the 'validFor' field of LEAR Credential Issuer's attributes' body
 */
class AttributeValidity{
    private LocalDateTime from;
    private LocalDateTime to;

    AttributeValidity(LocalDateTime from, LocalDateTime to){
        this.from = from;
        this.to = to;
    }

    @Schema(name = "from", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("from")
    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    @Schema(name = "to", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("to")
    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}

/**
 * Class representing the 'claims' field of LEAR Credential Issuer's attributes' body
 */
class AttributeClaim{
    private String name;
    private List<AttributeClaimAllowedValue> allowedValues;

    AttributeClaim(String name, @Nullable List<AttributeClaimAllowedValue> allowedValues){
        this.name = name;
        if(allowedValues==null) this.allowedValues = new ArrayList<>();
        else this.allowedValues = allowedValues;
    }

    public void addAllowedValue(AttributeClaimAllowedValue newValue){
        this.allowedValues.add(newValue);
    }

    @Schema(name = "name", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(name = "allowedValues", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("allowedValues")
    public List<AttributeClaimAllowedValue> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<AttributeClaimAllowedValue> allowedValues) {
        this.allowedValues = allowedValues;
    }
}

/**
 * Class representing an item from the 'allowedValues' array of the 'claims' field
 */
class AttributeClaimAllowedValue{
    private String value;
    @Nullable
    private String description;

    AttributeClaimAllowedValue(
        String value,
        @Nullable String description
    ){
        this.value = value;
        this.description = description;
    }

    @Schema(name = "value", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Schema(name = "description", requiredMode = RequiredMode.REQUIRED)
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}