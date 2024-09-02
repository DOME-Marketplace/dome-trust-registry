package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class InsertParticipantRequest {

  private String did;
  private Integer participantType;


  @Schema(name = "did", description = "DID of the participant to insert", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }


  public void setDid(String did) {
    this.did = did;
  }


  @Schema(name = "participantType", description = "Participant type 1 Active, 2 Revoked)", requiredMode = Schema.RequiredMode.REQUIRED)
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
    InsertParticipantRequest insertParticipantRequest = (InsertParticipantRequest) o;
    return Objects.equals(this.did, insertParticipantRequest.did);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InsertParticipantRequest {\n");
    sb.append("    did: ").append(toIndentedString(did)).append("\n");
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

