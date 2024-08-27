package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class InsertIssuerRequest {

  private String did;
  private Integer issuerType;
  private String issuerSpecificType;
  private String taoDid;

  @Schema(name = "did", description = "DID of the issuer to insert", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }


  public void setDid(String did) {
    this.did = did;
  }


  @Schema(name = "issuerType", description = "Issuer type 1 RootTAO, 2 TAO, 3 TI, 4 Revoked", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("issuerType")
  public Integer getIssuerType() {
    return issuerType;
  }


  public void setIssuerType(Integer issuerType) {
    this.issuerType = issuerType;
  }


  @Schema(name = "issuerSpecificType", description = "Specific type of the issuer", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("issuerSpecificType")
  public String getSpecificIssuerType() {
    return issuerSpecificType;
  }
  

  public void setSpecificIssuerType(String issuerSpecificType) {
    this.issuerSpecificType = issuerSpecificType;
  }


  @Schema(name = "taoDid", description = "DID of the TAO accrediting the issuer", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("taoDid")
  public String getTaoDid() {
    return taoDid;
  }


  public void setTaoDid(String taoDid) {
    this.taoDid = taoDid;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InsertIssuerRequest insertIssuerRequest = (InsertIssuerRequest) o;
    return Objects.equals(this.did, insertIssuerRequest.did) &&
        Objects.equals(this.issuerType, insertIssuerRequest.issuerType) &&
        Objects.equals(this.taoDid, insertIssuerRequest.taoDid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, issuerType, taoDid);
  }
}

