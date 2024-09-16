package com.digitelts.dome.trust.registry.model;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "listParticipants_200_response_links", description = "Links to other pages of results.")
@JsonTypeName("listParticipants_200_response_links")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListParticipants200ResponseLinks {
     private String first;
  private String prev;
  private String next;
  private String last;

  @Schema(name = "first", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("first")
  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }


  @Schema(name = "prev", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prev")
  public String getPrev() {
    return prev;
  }


  public void setPrev(String prev) {
    this.prev = prev;
  }


  @Schema(name = "next", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("next")
  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }


  @Schema(name = "last", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("last")
  public String getLast() {
    return last;
  }


  public void setLast(String last) {
    this.last = last;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListParticipants200ResponseLinks listParticipants200ResponseLinks = (ListParticipants200ResponseLinks) o;
    return Objects.equals(this.first, listParticipants200ResponseLinks.first) &&
        Objects.equals(this.prev, listParticipants200ResponseLinks.prev) &&
        Objects.equals(this.next, listParticipants200ResponseLinks.next) &&
        Objects.equals(this.last, listParticipants200ResponseLinks.last);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, prev, next, last);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListParticipants200ResponseLinks {\n");
    sb.append("    first: ").append(toIndentedString(first)).append("\n");
    sb.append("    prev: ").append(toIndentedString(prev)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    last: ").append(toIndentedString(last)).append("\n");
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
