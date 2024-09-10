package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantDetails {

  private String did;
  private String registrar;
  private String validFrom;
  private String validTo;


  
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of the participant.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }


  public void setDid(String did) {
    this.did = did;
  }


@Schema(name = "participantType", description = "The type of participant (1 Active, 2 Revoked).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("participantType")
  public Integer getParticipantType() {
    return participantType;
  }


  public void setParticipantType(Integer participantType) {
    this.participantType = participantType;
  }


 @Schema(name = "registrar", description = "The DID of the registrar registering the participant.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("registrar")
  public String getRegistrar() {
    return registrar;
  }


  public void setRegistrar(String registrar) {
    this.registrar = registrar;
  }

 @Schema(name = "validFrom", description = "The date from which the issuer is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("validFrom")
  public String getValidFrom() {
    return validFrom;
  }


  public void setValidFrom(String validFrom) {
    this.validFrom = validFrom;
  }


  @Schema(name = "validTo", description = "The date to which the issuer is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("validTo")
  public String getValidTo() {
    return validTo;
  }


  public void setValidTo(String validTo) {
    this.validTo = validTo;
  }
}

