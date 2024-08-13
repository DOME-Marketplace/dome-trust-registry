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
 * Attribute
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class Attribute {

  private String hash;

  private String body;

  private String issuerType;

  private String tao;

  private String rootTao;

  public Attribute hash(String hash) {
    this.hash = hash;
    return this;
  }

  /**
   * Hash of the attribute.
   * @return hash
   */
  
  @Schema(name = "hash", description = "Hash of the attribute.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hash")
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public Attribute body(String body) {
    this.body = body;
    return this;
  }

  /**
   * The attribute data as a JSON string.
   * @return body
   */
  
  @Schema(name = "body", description = "The attribute data as a JSON string.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("body")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Attribute issuerType(String issuerType) {
    this.issuerType = issuerType;
    return this;
  }

  /**
   * The type of issuer (e.g., RootTAO, TAO, TI, Revoked).
   * @return issuerType
   */
  
  @Schema(name = "issuerType", description = "The type of issuer (e.g., RootTAO, TAO, TI, Revoked).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issuerType")
  public String getIssuerType() {
    return issuerType;
  }

  public void setIssuerType(String issuerType) {
    this.issuerType = issuerType;
  }

  public Attribute tao(String tao) {
    this.tao = tao;
    return this;
  }

  /**
   * The DID of the TAO accrediting the issuer.
   * @return tao
   */
  
  @Schema(name = "tao", description = "The DID of the TAO accrediting the issuer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tao")
  public String getTao() {
    return tao;
  }

  public void setTao(String tao) {
    this.tao = tao;
  }

  public Attribute rootTao(String rootTao) {
    this.rootTao = rootTao;
    return this;
  }

  /**
   * The DID of the RootTAO accrediting the issuer.
   * @return rootTao
   */
  
  @Schema(name = "rootTao", description = "The DID of the RootTAO accrediting the issuer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rootTao")
  public String getRootTao() {
    return rootTao;
  }

  public void setRootTao(String rootTao) {
    this.rootTao = rootTao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Attribute attribute = (Attribute) o;
    return Objects.equals(this.hash, attribute.hash) &&
        Objects.equals(this.body, attribute.body) &&
        Objects.equals(this.issuerType, attribute.issuerType) &&
        Objects.equals(this.tao, attribute.tao) &&
        Objects.equals(this.rootTao, attribute.rootTao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash, body, issuerType, tao, rootTao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attribute {\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    issuerType: ").append(toIndentedString(issuerType)).append("\n");
    sb.append("    tao: ").append(toIndentedString(tao)).append("\n");
    sb.append("    rootTao: ").append(toIndentedString(rootTao)).append("\n");
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

