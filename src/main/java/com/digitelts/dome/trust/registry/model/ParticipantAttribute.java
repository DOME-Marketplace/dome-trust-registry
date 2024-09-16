package com.digitelts.dome.trust.registry.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ParticipantAttribute {
 private String participantType;

  @Schema(name = "participantType", description = "The type of participant (1 Active, 2 Revoked).", requiredMode = Schema.RequiredMode.REQUIRED)
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
