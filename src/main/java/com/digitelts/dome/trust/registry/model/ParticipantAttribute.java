package com.digitelts.dome.trust.registry.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ParticipantAttribute
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantAttribute {

  private String hash;

  private String body;

  private String participantType;

  public ParticipantAttribute hash(String hash) {
    this.hash = hash;
    return this;
  }

  /**
   * Hash of the attribute.
   * @return hash
   */
  
  @Schema(name = "hash", description = "Hash of the attribute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hash")
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public ParticipantAttribute body(String body) {
    this.body = body;
    return this;
  }

  /**
   * The attribute data as a JSON string.
   * @return body
   */
  
  @Schema(name = "body", description = "The attribute data as a JSON string.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("body")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public ParticipantAttribute participantType(String participantType) {
    this.participantType = participantType;
    return this;
  }

  /**
   * The type of participant (e.g. Active, Revoked).
   * @return participantType
   */
  
  @Schema(name = "participantType", description = "The type of participant (e.g. Active, Revoked).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantType")
  public String getParticipantType() {
    return participantType;
  }

  public void setParticipantType(String participantType) {
    this.participantType = participantType;
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
    return Objects.equals(this.hash, participantAttribute.hash) &&
        Objects.equals(this.body, participantAttribute.body) &&
        Objects.equals(this.participantType, participantAttribute.participantType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash, body, participantType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantAttribute {\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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

