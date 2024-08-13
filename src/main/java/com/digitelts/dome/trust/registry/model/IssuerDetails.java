package com.digitelts.dome.trust.registry.model;

import java.net.URI;
import java.util.Objects;
import com.digitelts.dome.trust.registry.model.Attribute;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IssuerDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class IssuerDetails {

  private String did;

  @Valid
  private List<@Valid Attribute> attributes = new ArrayList<>();

  public IssuerDetails did(String did) {
    this.did = did;
    return this;
  }

  /**
   * The Decentralized Identifier (DID) of the issuer.
   * @return did
   */
  
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of the issuer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public IssuerDetails attributes(List<@Valid Attribute> attributes) {
    this.attributes = attributes;
    return this;
  }

  public IssuerDetails addAttributesItem(Attribute attributesItem) {
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
  public List<@Valid Attribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<@Valid Attribute> attributes) {
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
    IssuerDetails issuerDetails = (IssuerDetails) o;
    return Objects.equals(this.did, issuerDetails.did) &&
        Objects.equals(this.attributes, issuerDetails.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IssuerDetails {\n");
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

