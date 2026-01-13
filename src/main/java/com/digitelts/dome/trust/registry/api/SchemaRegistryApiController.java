package com.digitelts.dome.trust.registry.api;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.digitelts.dome.trust.registry.exceptions.AuthException;
import com.digitelts.dome.trust.registry.model.*;
import com.digitelts.dome.trust.registry.repositories.SchemaRepository;
import com.digitelts.dome.trust.registry.services.AuthService;

import java.util.*;
import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class SchemaRegistryApiController extends RegistryApiController<SchemaDetails> implements SchemaRegistryApi {

    private final NativeWebRequest request;

    public SchemaRegistryApiController(NativeWebRequest request, SchemaRepository repo) {
        super(repo);
        this.request = request;
    }

    public SchemaRegistryApiController(NativeWebRequest request, SchemaRepository repo, AuthService auth) {
        super(repo, auth);
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getSchema(String schemaId) {
        SchemaDetails node = (SchemaDetails)findDetails(schemaId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Schema not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(node,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> registerSchema(@Valid SchemaDetails schemaDetails, String bearerToken) {
        try{
            if(this.insertRegistry(schemaDetails, bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Schema already exists"),HttpStatus.BAD_REQUEST);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateSchema(String schemaId, @Valid SchemaDetails updateSchemaRequest, String bearerToken) {
        try{
            if(this.updateRegistry(schemaId,updateSchemaRequest,bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Schema not found"), HttpStatus.NOT_FOUND);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ListSchemas200Response> listSchemas(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListSchemas200Response response = (ListSchemas200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListSchemas200Response(),
            new ListSchemas200ResponseLinks(),
            this.API_URL+"schemas?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "schemas/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSchema(String schemaId, String bearerToken) {
        try{
            deleteFromId(schemaId,bearerToken);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}