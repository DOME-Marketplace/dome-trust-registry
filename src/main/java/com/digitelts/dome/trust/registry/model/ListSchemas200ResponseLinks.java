package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "listSchema_200_response_links", description = "Links to other pages of results.")
@JsonTypeName("listSchema_200_response_links")
public class ListSchemas200ResponseLinks extends List200ResponseLinks{
	public ListSchemas200ResponseLinks(String first, String prev, String next, String last) {
		super(first, prev, next, last);
	}
	
	public ListSchemas200ResponseLinks(){}
}
