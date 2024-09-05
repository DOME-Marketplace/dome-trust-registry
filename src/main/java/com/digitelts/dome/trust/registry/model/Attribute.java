package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class Attribute {

  private String hash;
  private String body;
  private Integer issuerType;
  private String issuerSpecificType;
  private String tao;
  private String rootTao;


  @Schema(name = "hash", description = "Hash of the payload.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hash")
  public String getHash() {
    return hash;
  }


  public void setHash(String hash) {
    this.hash = hash;
  }


  @Schema(name = "body", description = "Base64 encoded content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("body")
  public String getBody() {
    return body;
  }


  public void setBody(String body) {
    this.body = body;
  }


  @Schema(name = "issuerSpecificType", description = "Specific type of the issuer", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("issuerSpecificType")
  public String getIssuerSpecificType() {
    return issuerSpecificType;
  }


  public void setIssuerSpecificType(String issuerSpecificType) {
    this.issuerSpecificType = issuerSpecificType;
  }


  @Schema(name = "issuerType", description = "Issuer type 1 RootTAO, 2 TAO, 3 TI, 4 Revoked", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("issuerType")
  public Integer getIssuerType() {
    return issuerType;
  }


  public void setIssuerType(Integer issuerType) {
    this.issuerType = issuerType;
  }


  @Schema(name = "tao", description = "The DID of the TAO accrediting the issuer.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tao")
  public String getTao() {
    return tao;
  }


  public void setTao(String tao) {
    this.tao = tao;
  }


  @Schema(name = "rootTao", description = "The DID of the RootTAO accrediting the issuer.", requiredMode = Schema.RequiredMode.REQUIRED)
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

