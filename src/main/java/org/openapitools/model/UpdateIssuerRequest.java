package org.openapitools.model;

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
 * UpdateIssuerRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T10:57:42.390843Z[UTC]", comments = "Generator version: 7.7.0")
public class UpdateIssuerRequest {

  private String did;

  private Integer issuerType;

  private String taoDid;

  public UpdateIssuerRequest did(String did) {
    this.did = did;
    return this;
  }

  /**
   * DID of the issuer
   * @return did
   */
  
  @Schema(name = "did", description = "DID of the issuer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("did")
  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public UpdateIssuerRequest issuerType(Integer issuerType) {
    this.issuerType = issuerType;
    return this;
  }

  /**
   * Issuer type 1 RootTAO, 2 TAO, 3 TI, 4 Revoked
   * @return issuerType
   */
  
  @Schema(name = "issuerType", description = "Issuer type 1 RootTAO, 2 TAO, 3 TI, 4 Revoked", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issuerType")
  public Integer getIssuerType() {
    return issuerType;
  }

  public void setIssuerType(Integer issuerType) {
    this.issuerType = issuerType;
  }

  public UpdateIssuerRequest taoDid(String taoDid) {
    this.taoDid = taoDid;
    return this;
  }

  /**
   * DID of the TAO accrediting the issuer
   * @return taoDid
   */
  
  @Schema(name = "taoDid", description = "DID of the TAO accrediting the issuer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    UpdateIssuerRequest updateIssuerRequest = (UpdateIssuerRequest) o;
    return Objects.equals(this.did, updateIssuerRequest.did) &&
        Objects.equals(this.issuerType, updateIssuerRequest.issuerType) &&
        Objects.equals(this.taoDid, updateIssuerRequest.taoDid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did, issuerType, taoDid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateIssuerRequest {\n");
    sb.append("    did: ").append(toIndentedString(did)).append("\n");
    sb.append("    issuerType: ").append(toIndentedString(issuerType)).append("\n");
    sb.append("    taoDid: ").append(toIndentedString(taoDid)).append("\n");
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

