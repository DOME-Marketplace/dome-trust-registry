package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Abstract class with common methods and attributes
 * @see ListAccessNodes200ResponseLinks
 * @see ListIssuers200ResponseLinks
 * @see ListParticipants200ResponseLinks
 */
public abstract class List200ResponseLinks {
    
    protected String first;
    protected String prev;
    protected String next;
    protected String last;

    // BUILDERS //
    public List200ResponseLinks(String first, String prev, String next, String last){
        this.first = first;
        this.prev = prev;
        this.next = next;
        this.last = last;
    }

    public List200ResponseLinks(){
        this.prev = null;
        this.next = null;
    }

    // GETTERS //
    @Schema(name = "first", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("first")
    public String getFirst() {
        return first;
    }

    @Schema(name = "prev", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("prev")
    public String getPrev() {
        return prev;
    }

    @Schema(name = "next", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @Schema(name = "last", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("last")
    public String getLast() {
        return last;
    }

    // SETTERS //
    public void setFirst(String first) {
        this.first = first;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
