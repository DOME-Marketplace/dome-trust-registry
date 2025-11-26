package com.digitelts.dome.trust.registry.model;

import javax.annotation.Nullable;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

/**
 * Abstract class with common methods and attributes
 * @see ListAccessNodes200Response
 * @see ListIssuers200Response
 * @see ListParticipants200Response
 */
public abstract class List200Response {

    protected String self;
    @Valid
    protected List<TrustedRegistrySummary> items;
    protected Integer total;
    protected Integer pageSize;
    protected List200ResponseLinks links;

    // BUILDERS //
    public List200Response(){
        this.items = new ArrayList<>();
    }

    public List200Response(String self, List<TrustedRegistrySummary> items, Integer total, Integer pageSize, List200ResponseLinks links){
        this.self = self;
        this.items = items;
        this.total = total;
        this.pageSize = pageSize;
        this.links = links;
    }

    // GETTERS //
    @Schema(name = "self", description = "The URL of the current page of results.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("self")
    public String getSelf() {
        return this.self;
    }

    @Valid 
    @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("items")
    public List<TrustedRegistrySummary> getItems() {
        return this.items;
    }

    @Schema(name = "total", description = "The total number of access nodes in the registry.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("total")
    public Integer getTotal() {
        return this.total;
    }

    @Schema(name = "pageSize", description = "The number of items per page.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return this.pageSize;
    }

    @Valid 
    @Schema(name = "links", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("links")
    public List200ResponseLinks getLinks() {
        return this.links;
    }

    // SETTERS //
    public void setSelf(String self) {
        this.self = self;
    }

    public void setItems(List<@Valid TrustedRegistrySummary> items) {
        this.items = items;
        this.total = this.items.size();
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setLinks(List200ResponseLinks links) {
        this.links = links;
    }

    public void addItem(@Valid TrustedRegistrySummary item){
        this.items.add(item);
        this.total += 1;
    }

    @Nullable
    public @Valid TrustedRegistrySummary getItemAt(int index){
        if(index<0 || index>this.total){
            return null;
        }
        return this.items.get(index);
    }
}
