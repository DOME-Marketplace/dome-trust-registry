package com.digitelts.dome.trust.registry.model;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantSummary {

  private String did;
  private String href;

  
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of the participant.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  // Page simulation builder
  public ParticipantSummary(String did, String href) {
    this.did = did;
    this.href = href;
  }

  public void setDid(String did) {
    this.did = did;
  }

  @Schema(name = "href", description = "The link to the details of the participant.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("href")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParticipantSummary participantSummary = (ParticipantSummary) o;
    return Objects.equals(this.did, participantSummary.did);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParticipantSummary {\n");
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

