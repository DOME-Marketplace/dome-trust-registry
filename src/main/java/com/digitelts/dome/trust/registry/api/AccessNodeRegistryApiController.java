package com.digitelts.dome.trust.registry.api;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.digitelts.dome.trust.registry.exceptions.AuthException;
import com.digitelts.dome.trust.registry.model.*;
import com.digitelts.dome.trust.registry.repositories.AccessNodeRepository;
import com.digitelts.dome.trust.registry.services.AuthService;

import java.util.*;
import javax.annotation.Generated;
import javax.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class AccessNodeRegistryApiController extends RegistryApiController<AccessNodeDetails> implements AccessNodeRegistryApi {

    private final NativeWebRequest request;

    public AccessNodeRegistryApiController(NativeWebRequest request, AccessNodeRepository repo) {
        super(repo);
        this.request = request;
    }

    public AccessNodeRegistryApiController(NativeWebRequest request, AccessNodeRepository repo, AuthService auth) {
        super(repo, auth);
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getAccessNode(String accessNodeId) {
        AccessNodeDetails node = (AccessNodeDetails)findDetails(accessNodeId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Access Node not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(node,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerAccessNode(@Valid AccessNodeDetails accessNodeDetails, String bearerToken) {
        try{
        if(this.insertRegistry(accessNodeDetails, bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Access Node already exists"),HttpStatus.BAD_REQUEST);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateAccessNode(String accessNodeId, @Valid AccessNodeDetails updateDidRequest, String bearerToken) {
        try{
        if(this.updateRegistry(accessNodeId,updateDidRequest,bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Access Node not found"), HttpStatus.NOT_FOUND);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ListAccessNodes200Response> listAccessNodes(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListAccessNodes200Response response = (ListAccessNodes200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListAccessNodes200Response(),
            new ListAccessNodes200ResponseLinks(),
            this.API_URL+"accessNodes?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "accessNodes/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
