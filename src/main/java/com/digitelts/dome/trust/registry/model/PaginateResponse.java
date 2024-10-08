package com.digitelts.dome.trust.registry.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class PaginateResponse<T> {
     private String self;
    @Valid
    private List<@Valid T> items = new ArrayList<>();
    private Integer total;
    private Integer pageSize;
    private ResponseLinks links;

    @Schema(name = "self", description = "The URL of the current page of results.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("self")
    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Valid
    @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("items")
    public List<@Valid T> getItems() {
        return items;
    }

    public void setItems(List<@Valid T> items) {
        this.items = items;
    }

    @Schema(name = "total", description = "The total number of items.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Schema(name = "pageSize", description = "The number of items per page.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Valid
    @Schema(name = "links", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("links")
    public ResponseLinks getLinks() {
        return links;
    }

    public void setLinks(ResponseLinks links) {
        this.links = links;
    }

}
