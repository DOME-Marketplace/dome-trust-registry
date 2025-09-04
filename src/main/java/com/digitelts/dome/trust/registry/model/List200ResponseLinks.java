package com.digitelts.dome.trust.registry.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase abstracta con métodos y atributos comunes
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

    // OTHER METHODS //
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        List200ResponseLinks that = (List200ResponseLinks) o;
        return Objects.equals(this.first, that.first) &&
                Objects.equals(this.prev, that.prev) &&
                Objects.equals(this.next, that.next) &&
                Objects.equals(this.last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, prev, next, last);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class "+this.getClass().getSimpleName()+" {\n");
        sb.append("    first: ").append(toIndentedString(first)).append("\n");
        sb.append("    prev: ").append(toIndentedString(prev)).append("\n");
        sb.append("    next: ").append(toIndentedString(next)).append("\n");
        sb.append("    last: ").append(toIndentedString(last)).append("\n");
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
