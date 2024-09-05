package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantAttribute {

  private String hash;
  private String body;
  private Integer participantType;
  private String registrar;

  @Schema(name = "hash", description = "Hash of the payload.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hash")
  public String getHash() {
    return hash;
  }


  public void setHash(String hash) {
    this.hash = hash;
  }


  @Schema(name = "body", description = "Base64 encoded content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("body")
  public String getBody() {
    return body;
  }


  public void setBody(String body) {
    this.body = body;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParticipantAttribute participantAttribute = (ParticipantAttribute) o;
    return Objects.equals(this.participantType, participantAttribute.participantType);
  }


  @Override
  public int hashCode() {
    return Objects.hash(participantType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantAttribute {\n");
    sb.append("    participantType: ").append(toIndentedString(participantType)).append("\n");
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

