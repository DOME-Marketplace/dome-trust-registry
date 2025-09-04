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
public class SchemaRegistryApiController extends ApiController implements SchemaRegistryApi {

    private final NativeWebRequest request;

    public SchemaRegistryApiController(NativeWebRequest request) {
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
    public ResponseEntity<WrongRequest> registerSchema(@Valid SchemaDetails schemaDetails) {
        if(this.detailsList.contains(schemaDetails)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Invalid Schema"),HttpStatus.BAD_REQUEST);
        this.detailsList.add(schemaDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> updateSchema(String schemaId, @Valid SchemaDetails updateDidRequest) {
        SchemaDetails node = (SchemaDetails)findDetails(schemaId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Schema not found"), HttpStatus.NOT_FOUND);
        node.setDid(updateDidRequest.getDid());
        node.setValidFrom(updateDidRequest.getValidFrom());
        node.setValidTo(updateDidRequest.getValidTo());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListSchemas200Response> listSchemas(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListSchemas200Response response = (ListSchemas200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListSchemas200Response(),
            new ListSchemas200ResponseLinks(),
            "http://localhost:8080/v4/schemas?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "schemas/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> deleteSchema(String schemaId) {
        if(this.deleteFromId(schemaId)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Schema not found"),HttpStatus.NOT_FOUND);
    }
}