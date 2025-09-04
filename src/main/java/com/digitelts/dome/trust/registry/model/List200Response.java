package com.digitelts.dome.trust.registry.model;

import javax.annotation.Nullable;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

/**
 * Clase abstracta con métodos y atributos
 * @see ListAccessNodes200Response
 * @see ListIssuers200Response
 * @see ListParticipants200Response
 */
public abstract class List200Response {

    protected String self;
    @Valid
    protected List<Summary> items;
    protected Integer total;
    protected Integer pageSize;
    protected List200ResponseLinks links;

    // BUILDERS //
    public List200Response(){
        this.items = new ArrayList<>();
    }

    public List200Response(String self, List<Summary> items, Integer total, Integer pageSize, List200ResponseLinks links){
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
    public List<Summary> getItems() {
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

    public void setItems(List<@Valid Summary> items) {
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

    // OTHER METHODS //
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        List200Response that = (List200Response) o;
        return Objects.equals(this.self, that.getSelf()) &&
               Objects.equals(this.items, that.getItems()) &&
               Objects.equals(this.total, that.getTotal()) &&
               Objects.equals(this.pageSize, that.getPageSize()) &&
               Objects.equals(this.links, that.getLinks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.self, this.items, this.total, this.pageSize, this.links);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class "+this.getClass().getSimpleName()+" {\n");
        sb.append("    self: ").append(toIndentedString(this.self)).append("\n");
        sb.append("    items: ").append(toIndentedString(this.items)).append("\n");
        sb.append("    total: ").append(toIndentedString(this.total)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(this.pageSize)).append("\n");
        sb.append("    links: ").append(toIndentedString(this.links)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public void addItem(@Valid Summary item){
        this.items.add(item);
        this.total += 1;
    }

    @Nullable
    public @Valid Summary getItemAt(int index){
        if(index<0 || index>this.total){
            return null;
        }
        return this.items.get(index);
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
