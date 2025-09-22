package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class AccessNodeDetails extends TrustedRegistryDetails {
    
    @NotNull
    private String name;

    public AccessNodeDetails(@NotNull String address, @NotNull String name){
        super(address);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Schema(name = "name", description = "The name of the Access Node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @Override
    @Schema(name = "address", description = "The DLT address of the Access Node.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("address")
    public String getId(){
        return this.id;
    }

    @Override
    public AccessNodeSummary getSummary(String url){
        return new AccessNodeSummary(this.id, url+this.id);
    }
}
