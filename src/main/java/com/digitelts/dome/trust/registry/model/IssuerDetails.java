package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class IssuerDetails {

  private String did;
  @Valid
  private Integer issuerType;
  private String issuerSpecificType;
  private String tao;
  private String rootTao;
  private String validFrom;
  private String validTo;

  @Schema(name = "did", description = "The Decentralized Identifier (DID) of the issuer.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }


  public void setDid(String did) {
    this.did = did;
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


  @Schema(name = "validFrom", description = "The date from which the issuer is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("validFrom")
  public String getValidFrom() {
    return validFrom;
  }


  public void setValidFrom(String validFrom) {
    this.validFrom = validFrom;
  }


  @Schema(name = "validTo", description = "The date to which the issuer is valid in ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("validTo")
  public String getValidTo() {
    return validTo;
  }


  public void setValidTo(String validTo) {
    this.validTo = validTo;
  }
}

