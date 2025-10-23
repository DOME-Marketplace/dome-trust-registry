package com.digitelts.dome.trust.registry.model;

import javax.annotation.Generated;
import java.util.*;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;

@Entity
@Table(name = "lear_credential_issuers")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class LEARCredentialIssuerDetails extends TrustedRegistryDetails {
  
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "issuer_id")
  protected List<Attribute> attributes;
  protected boolean hasAttributes;

  public LEARCredentialIssuerDetails(@NonNull String id, List<Attribute> attributes){
    super(id);
    this.attributes = attributes;
    this.hasAttributes = !attributes.isEmpty();
  }

  public LEARCredentialIssuerDetails(){}

  @Override
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of this LEAR Credential Issuer.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getId(){
      return this.id;
  }

  @Override
  public LEARCredentialIssuerSummary getSummary(String url){
    return new LEARCredentialIssuerSummary(this.id, url+this.id);
  }


  @Schema(name = "attributes", description = "A list of attributes for this LEAR Credential Issuer")
  @JsonProperty("attributes")
  public List<Attribute> getAttributes() {
    return attributes;
  }

  @Schema(name = "hasAttributes", description = "A flag indicating whether this LEAR Credential Issuer has attributes or not")
  @JsonProperty("hasAttributes")
  public boolean isHasAttributes() {
    return hasAttributes;
  }


  public void setAttributes(List<Attribute> attributes) {
    this.attributes = attributes;
    this.hasAttributes = !attributes.isEmpty();
  }
}

