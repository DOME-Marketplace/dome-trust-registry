package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@JsonTypeName("listIssuers_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListIssuers200Response {

  private String self;
  @Valid
  private List<@Valid IssuerSummary> items = new ArrayList<>();
  private Integer total;
  private Integer pageSize;
  private ListIssuers200ResponseLinks links;

  
  @Schema(name = "self", description = "The URL of the current page of results.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("self")
  public String getSelf() {
    return self;
  }


  public void setSelf(String self) {
    this.self = self;
  }


  @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("items")
  public List<@Valid IssuerSummary> getItems() {
    return items;
  }


  public void setItems(List<@Valid IssuerSummary> items) {
    this.items = items;
  }


  public ListIssuers200Response total(Integer total) {
    this.total = total;
    return this;
  }


  @Schema(name = "total", description = "The total number of issuers in the registry.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }


  public void setTotal(Integer total) {
    this.total = total;
  }


  @Schema(name = "pageSize", description = "The number of items per page.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }


  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }


  @Valid 
  @Schema(name = "links", requiredMode = Schema.RequiredMode.REQUIRED)
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
    ListIssuers200Response listIssuers200Response = (ListIssuers200Response) o;
    return Objects.equals(this.self, listIssuers200Response.self) &&
        Objects.equals(this.items, listIssuers200Response.items) &&
        Objects.equals(this.total, listIssuers200Response.total) &&
        Objects.equals(this.pageSize, listIssuers200Response.pageSize) &&
        Objects.equals(this.links, listIssuers200Response.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, items, total, pageSize, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListIssuers200Response {\n");
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

