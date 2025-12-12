package com.digitelts.dome.trust.registry.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import javax.validation.Valid;
import java.util.*;
import javax.annotation.Generated;

import com.digitelts.dome.trust.registry.exceptions.AuthException;
import com.digitelts.dome.trust.registry.model.*;
import com.digitelts.dome.trust.registry.repositories.LEARCredentialIssuerRepository;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class LEARCredentialIssuerApiController extends RegistryApiController<LEARCredentialIssuerDetails> implements LEARCredentialIssuerApi {

    private final NativeWebRequest request;

    public LEARCredentialIssuerApiController(NativeWebRequest request, LEARCredentialIssuerRepository repo) {
        super(repo);
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
                this.API_URL+"issuers?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
                "issuers/"                   
            );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getIssuer(String issuerId) {
        LEARCredentialIssuerDetails response = (LEARCredentialIssuerDetails)findDetails(issuerId);

        if(response == null){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(),"LEAR Credential Issuer not found"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> insertIssuer(@Valid LEARCredentialIssuerDetails insertIssuerRequest, String bearerToken) {
        try{
        if(this.insertRegistry(insertIssuerRequest, bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "LEAR Credential Ussuer already exists"), HttpStatus.BAD_REQUEST);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateIssuer(String issuerId, @Valid LEARCredentialIssuerDetails updateIssuerRequest, String bearerToken) {
        try{
        if(this.updateRegistry(issuerId,updateIssuerRequest,bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "LEAR Credential Issuer not found"), HttpStatus.NOT_FOUND);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
