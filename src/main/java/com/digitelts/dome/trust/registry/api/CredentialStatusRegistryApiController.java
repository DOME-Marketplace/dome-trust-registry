package com.digitelts.dome.trust.registry.api;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.model.*;
import java.util.*;
import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class CredentialStatusRegistryApiController extends ApiController implements CredentialStatusRegistryApi {

    private final NativeWebRequest request;

    public CredentialStatusRegistryApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getCredentialStatus(String credentialStatusId) {
        CredentialStatusDetails node = (CredentialStatusDetails)findDetails(credentialStatusId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Credential Status not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(node,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> registerCredentialStatus(@Valid CredentialStatusDetails credentialStatusDetails) {
        this.detailsList.add(credentialStatusDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> updateCredentialStatus(String credentialStatusId, @Valid CredentialStatusDetails updateDidRequest) {
        CredentialStatusDetails node = (CredentialStatusDetails)findDetails(credentialStatusId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Credential Status not found"), HttpStatus.NOT_FOUND);
        node.setDid(updateDidRequest.getDid());
        node.setValidFrom(updateDidRequest.getValidFrom());
        node.setValidTo(updateDidRequest.getValidTo());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListCredentialStatuses200Response> listCredentialStatuses(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListCredentialStatuses200Response response = (ListCredentialStatuses200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListCredentialStatuses200Response(),
            new ListCredentialStatuses200ResponseLinks(),
            "http://localhost:8080/v4/credentialStatuses?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "credentialStatuses/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}