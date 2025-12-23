package com.digitelts.dome.trust.registry.model;

import java.util.*;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("listParticipants_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListParticipants200Response extends List200Response{

    public ListParticipants200Response(String self, List<TrustedRegistrySummary> items, Integer total, Integer pageSize, ListParticipants200ResponseLinks links){
        super(self, items, total, pageSize, links);
    }

    public ListParticipants200Response(){}
}
