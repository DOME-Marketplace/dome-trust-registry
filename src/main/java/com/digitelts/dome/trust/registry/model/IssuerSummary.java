package com.digitelts.dome.trust.registry.model;

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
 * IssuerSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class IssuerSummary {

  private String did;

  private String href;

  public IssuerSummary did(String did) {
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

  public IssuerSummary href(String href) {
    this.href = href;
    return this;
  }

  /**
   * The link to the details of the issuer.
   * @return href
   */
  
  @Schema(name = "href", description = "The link to the details of the issuer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    IssuerSummary issuerSummary = (IssuerSummary) o;
    return Objects.equals(this.did, issuerSummary.did) &&
        Objects.equals(this.href, issuerSummary.href);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, href);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IssuerSummary {\n");
    sb.append("    did: ").append(toIndentedString(did)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
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

