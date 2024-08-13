package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.ListIssuers200ResponseLinks;
import org.openapitools.model.ParticipantSummary;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ListParticipants200Response
 */

@JsonTypeName("listParticipants_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T10:57:42.390843Z[UTC]", comments = "Generator version: 7.7.0")
public class ListParticipants200Response {

  private String self;

  @Valid
  private List<@Valid ParticipantSummary> items = new ArrayList<>();

  private Integer total;

  private Integer pageSize;

  private ListIssuers200ResponseLinks links;

  public ListParticipants200Response self(String self) {
    this.self = self;
    return this;
  }

  /**
   * The URL of the current page of results.
   * @return self
   */
  
  @Schema(name = "self", description = "The URL of the current page of results.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("self")
  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public ListParticipants200Response items(List<@Valid ParticipantSummary> items) {
    this.items = items;
    return this;
  }

  public ListParticipants200Response addItemsItem(ParticipantSummary itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   */
  @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("items")
  public List<@Valid ParticipantSummary> getItems() {
    return items;
  }

  public void setItems(List<@Valid ParticipantSummary> items) {
    this.items = items;
  }

  public ListParticipants200Response total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * The total number of participants in the registry.
   * @return total
   */
  
  @Schema(name = "total", description = "The total number of participants in the registry.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public ListParticipants200Response pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * The number of items per page.
   * @return pageSize
   */
  
  @Schema(name = "pageSize", description = "The number of items per page.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public ListParticipants200Response links(ListIssuers200ResponseLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
   */
  @Valid 
  @Schema(name = "links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("links")
  public ListIssuers200ResponseLinks getLinks() {
    return links;
  }

  public void setLinks(ListIssuers200ResponseLinks links) {
    this.links = links;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListParticipants200Response listParticipants200Response = (ListParticipants200Response) o;
    return Objects.equals(this.self, listParticipants200Response.self) &&
        Objects.equals(this.items, listParticipants200Response.items) &&
        Objects.equals(this.total, listParticipants200Response.total) &&
        Objects.equals(this.pageSize, listParticipants200Response.pageSize) &&
        Objects.equals(this.links, listParticipants200Response.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, items, total, pageSize, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListParticipants200Response {\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

