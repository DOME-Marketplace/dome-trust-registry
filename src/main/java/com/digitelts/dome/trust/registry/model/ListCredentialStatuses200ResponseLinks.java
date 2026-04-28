package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "listCredentialStatus_200_response_links", description = "Links to other pages of results.")
@JsonTypeName("listCredentialStatus_200_response_links")
public class ListCredentialStatuses200ResponseLinks extends List200ResponseLinks{
    
    public ListCredentialStatuses200ResponseLinks(String first, String prev, String next, String last) {
		super(first, prev, next, last);
	}
	
	public ListCredentialStatuses200ResponseLinks(){}
}
