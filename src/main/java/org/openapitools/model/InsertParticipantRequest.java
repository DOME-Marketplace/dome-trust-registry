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
 * InsertParticipantRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T10:57:42.390843Z[UTC]", comments = "Generator version: 7.7.0")
public class InsertParticipantRequest {

  private String did;

  public InsertParticipantRequest did(String did) {
    this.did = did;
    return this;
  }

  /**
   * DID of the participant to insert
   * @return did
   */
  
  @Schema(name = "did", description = "DID of the participant to insert", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
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

