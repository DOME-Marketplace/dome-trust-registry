package com.digitelts.dome.trust.registry.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import javax.validation.Valid;
import java.util.*;
import javax.annotation.Generated;
import com.digitelts.dome.trust.registry.model.*;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class LEARCredentialIssuerApiController extends RegistryApiController implements LEARCredentialIssuerApi {

    private final NativeWebRequest request;

    public LEARCredentialIssuerApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ListIssuers200Response> listIssuers(@Valid Integer pageAfter, @Valid Integer pageSize) {

        ListIssuers200Response response = (ListIssuers200Response) this.listDetails(
                pageAfter,
                pageSize,
                new ListIssuers200Response(),
                new ListIssuers200ResponseLinks(),
                "http://localhost:8080/v4/issuers?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
                "issuers/"                   
            );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getIssuer(String issuerId) {
        LEARCredentialIssuerDetails response = (LEARCredentialIssuerDetails)findDetails(issuerId);

        if(response == null){
            System.out.println("Issuer with ID: "+issuerId+" not found");
            return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(),"Issuer not found"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> insertIssuer(@Valid LEARCredentialIssuerDetails insertIssuerRequest) {
        if(this.detailsList.contains(insertIssuerRequest)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "LEAR Credential Issuer already exists"),HttpStatus.BAD_REQUEST);
        this.detailsList.add(insertIssuerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateIssuer(String issuerId, @Valid LEARCredentialIssuerDetails updateIssuerRequest) {
        LEARCredentialIssuerDetails issuer = (LEARCredentialIssuerDetails)findDetails(issuerId);
        if(issuer==null){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Issuer not found"),HttpStatus.NOT_FOUND);
        }
        issuer.setId(updateIssuerRequest.getId());
        issuer.setValidFrom(updateIssuerRequest.getValidFrom());
        issuer.setValidTo(updateIssuerRequest.getValidTo());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteIssuer(String issuerId) {
        deleteFromId(issuerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
