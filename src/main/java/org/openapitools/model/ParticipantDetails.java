package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.ParticipantAttribute;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ParticipantDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T10:57:42.390843Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantDetails {

  private String did;

  @Valid
  private List<@Valid ParticipantAttribute> attributes = new ArrayList<>();

  public ParticipantDetails did(String did) {
    this.did = did;
    return this;
  }

  /**
   * The Decentralized Identifier (DID) of the participant.
   * @return did
   */
  
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of the participant.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public ParticipantDetails attributes(List<@Valid ParticipantAttribute> attributes) {
    this.attributes = attributes;
    return this;
  }

  public ParticipantDetails addAttributesItem(ParticipantAttribute attributesItem) {
    if (this.attributes == null) {
      this.attributes = new ArrayList<>();
    }
    this.attributes.add(attributesItem);
    return this;
  }

  /**
   * Get attributes
   * @return attributes
   */
  @Valid 
  @Schema(name = "attributes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attributes")
  public List<@Valid ParticipantAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<@Valid ParticipantAttribute> attributes) {
    this.attributes = attributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParticipantDetails participantDetails = (ParticipantDetails) o;
    return Objects.equals(this.did, participantDetails.did) &&
        Objects.equals(this.attributes, participantDetails.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantDetails {\n");
    sb.append("    did: ").append(toIndentedString(did)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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

