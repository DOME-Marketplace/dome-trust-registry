package com.digitelts.dome.trust.registry.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "participants_200_response_links", description = "Links to other pages of results.")
@JsonTypeName("participants_200_response_links")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListParticipants200ResponseLinks extends List200ResponseLinks{

    public ListParticipants200ResponseLinks(String first, String prev, String next, String last) {
        super(first, prev, next, last);
    }

    public ListParticipants200ResponseLinks(){}
}
