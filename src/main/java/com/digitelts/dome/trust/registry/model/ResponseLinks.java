package com.digitelts.dome.trust.registry.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseLinks {

     private String first;
    private String prev;
    private String next;
    private String last;

    @Schema(name = "first", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("first")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Schema(name = "prev", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("prev")
    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    @Schema(name = "next", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Schema(name = "last", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("last")
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseLinks that = (ResponseLinks) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(prev, that.prev) &&
                Objects.equals(next, that.next) &&
                Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, prev, next, last);
    }

    @Override
    public String toString() {
        return "ResponseLinks{" +
                "first='" + first + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
