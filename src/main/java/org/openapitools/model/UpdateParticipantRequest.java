package org.openapitools.model;

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
 * UpdateParticipantRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T10:57:42.390843Z[UTC]", comments = "Generator version: 7.7.0")
public class UpdateParticipantRequest {

  private String did;

  private Integer participantType;

  public UpdateParticipantRequest did(String did) {
    this.did = did;
    return this;
  }

  /**
   * DID of the participant
   * @return did
   */
  
  @Schema(name = "did", description = "DID of the participant", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public UpdateParticipantRequest participantType(Integer participantType) {
    this.participantType = participantType;
    return this;
  }

  /**
   * Participant type (e.g., Active, Revoked)
   * @return participantType
   */
  
  @Schema(name = "participantType", description = "Participant type (e.g., Active, Revoked)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("participantType")
  public Integer getParticipantType() {
    return participantType;
  }

  public void setParticipantType(Integer participantType) {
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
    UpdateParticipantRequest updateParticipantRequest = (UpdateParticipantRequest) o;
    return Objects.equals(this.did, updateParticipantRequest.did) &&
        Objects.equals(this.participantType, updateParticipantRequest.participantType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, participantType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateParticipantRequest {\n");
    sb.append("    did: ").append(toIndentedString(did)).append("\n");
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

