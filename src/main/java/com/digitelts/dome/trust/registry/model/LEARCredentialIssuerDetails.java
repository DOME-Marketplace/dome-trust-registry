package com.digitelts.dome.trust.registry.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.annotation.Nullable;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class LEARCredentialIssuerDetails extends TrustedRegistryDetails {
  
  @Nullable
  protected LocalDateTime validFrom;
  @Nullable
  protected LocalDateTime validTo;

  public LEARCredentialIssuerDetails(@NonNull String id, @NonNull LocalDateTime from, @NonNull LocalDateTime to){
    super(id);
    this.validFrom = from;
    this.validTo = to;
  }

  public void setValidFrom(LocalDateTime from){
      this.validFrom = from;
  }

  public void setValidTo(LocalDateTime to){
      this.validTo = to;
  }

  @Schema(name = "validFrom", description = "The date from which this item is valid in ISO 8601. Only required for Trusted Participants Registries and LEAR Credential Issuers.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validFrom")
  @Nullable
  public LocalDateTime getValidFrom() {
      return this.validFrom;
  }

  @Schema(name = "validTo", description = "The date to which this item is valid in ISO 8601. Only required for Trusted Participants Registries and LEAR Credential Issuers", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validTo")
  @Nullable
  public LocalDateTime getValidTo() {
      return this.validTo;
  }

  @Override
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of this LEAR Credential Issuer.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getId(){
      return this.id;
  }

  @Override
  public LEARCredentialIssuerSummary getSummary(String url){
    return new LEARCredentialIssuerSummary(this.id, url+this.id);
  }
}

