package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.annotation.Generated;

@Schema(name = "listIssuers_200_response_links", description = "Links to other pages of results.")
@JsonTypeName("listIssuers_200_response_links")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListIssuers200ResponseLinks extends List200ResponseLinks{

  public ListIssuers200ResponseLinks(String first, String prev, String next, String last) {
    super(first, prev, next, last);
  }

  public ListIssuers200ResponseLinks(){}
}

