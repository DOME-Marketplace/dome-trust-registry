package com.digitelts.dome.trust.registry.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.*;
import javax.annotation.Generated;

@JsonTypeName("listCredentialStatus_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ListCredentialStatuses200Response extends List200Response {

    public ListCredentialStatuses200Response(String self, List<TrustedRegistrySummary> items, Integer total, Integer pageSize, ListCredentialStatuses200ResponseLinks links){
        super(self, items, total, pageSize, links);
    }

    public ListCredentialStatuses200Response(){}
}
