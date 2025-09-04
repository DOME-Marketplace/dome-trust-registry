package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantDetails extends TrustedRegistryDetails{

  private String registrar;

  public ParticipantDetails(String id, String reg, LocalDateTime from, LocalDateTime to){
    super(id, from, to);
    this.registrar = reg;
  }

  @Schema(name = "registrar", description = "The DID of the registrar registering the participant.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("registrar")
  public String getRegistrar() {
    return registrar;
  }

  public void setRegistrar(String registrar) {
    this.registrar = registrar;
  }

  @Override
  public ParticipantSummary getSummary(String url){
    return new ParticipantSummary(this.did, url+this.did);
  }
}

