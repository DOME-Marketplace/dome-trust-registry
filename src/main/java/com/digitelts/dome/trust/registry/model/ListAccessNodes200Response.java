package com.digitelts.dome.trust.registry.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("listAccessNodes_200_response")
public class ListAccessNodes200Response extends List200Response{

    public ListAccessNodes200Response(String self, List<Summary> items, Integer total, Integer pageSize, ListAccessNodes200ResponseLinks links){
        super(self, items, total, pageSize, links);
    }

    public ListAccessNodes200Response(){}
}