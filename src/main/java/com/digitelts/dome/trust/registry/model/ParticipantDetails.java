package com.digitelts.dome.trust.registry.model;

import javax.annotation.Generated;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "participants")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantDetails extends TrustedRegistryDetails{

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "participant_id")
  protected List<Attribute> attributes;
  protected boolean hasAttributes;

  public ParticipantDetails(@JsonProperty("did") @NonNull String id){
    super(id);
    this.attributes = new ArrayList<>();
    this.hasAttributes = false;
  }

  public ParticipantDetails(){}

  @Override
  @Schema(name = "did", description = "The Decentralized Identifier (DID) of this participant.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("did")
  public String getId(){
      return super.getId();
  }

  @Override
  public ParticipantSummary getSummary(String url){
    return new ParticipantSummary(this.id, url+this.id);
  }

  @Schema(name = "attributes", description = "Empty list", defaultValue = "[]")
  @JsonProperty("attributes")
  public List<Attribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<Attribute> attributes) {
    this.attributes = attributes;
  }

  @Schema(name = "hasAttributes", description = "Whether the attributes list is empty or not", defaultValue = "false")
  @JsonProperty("hasAttributes")
  public boolean isHasAttributes() {
    return hasAttributes;
  }

  public void setHasAttributes(boolean hasAttributes) {
    this.hasAttributes = hasAttributes;
  }
}

