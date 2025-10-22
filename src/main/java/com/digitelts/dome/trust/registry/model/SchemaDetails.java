package com.digitelts.dome.trust.registry.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;

@Entity
@Table(name = "schemas")
public class SchemaDetails extends TrustedRegistryDetails{

    @NotNull
    private String schemaData;

    public SchemaDetails(String id, String schemaData) {
        super(id);
        this.schemaData = schemaData;
    }

    public SchemaDetails(){}

    @Schema(name = "schemaData", description = "The JSON representation of the Schema", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("schemaData")
    public String getSchemaData(){
        return schemaData;
    }

    public void setSchemaData(String data){
        this.schemaData = data;
    }

    @Override
    public SchemaSummary getSummary(String url) {
        return new SchemaSummary(this.id, url+this.id);
    }
    
}
