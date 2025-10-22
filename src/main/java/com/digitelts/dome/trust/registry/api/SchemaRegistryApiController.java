package com.digitelts.dome.trust.registry.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.model.*;
import java.util.*;
import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class SchemaRegistryApiController extends RegistryApiController<SchemaDetails> implements SchemaRegistryApi {

    private final NativeWebRequest request;

    // @Value("${HOST_URL}") // <= If running in Docker
    @Value("http://localhost") // <= If running in local
    private String host;
    // @Value("${PORT}") // <= If running in Docker
    @Value("8080") // <= If running in local
    private String port;

    public SchemaRegistryApiController(NativeWebRequest request) {
        super(null);
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
    public ResponseEntity<?> registerSchema(@Valid SchemaDetails schemaDetails) {
        // if(this.detailsList.contains(schemaDetails)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Schema already exists"),HttpStatus.BAD_REQUEST);
        // this.detailsList.add(schemaDetails);
        // return new ResponseEntity<>(HttpStatus.OK);
        if(this.insertRegistry(schemaDetails)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Access Node already exists"),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> updateSchema(String schemaId, @Valid SchemaDetails updateSchemaRequest) {
        // SchemaDetails schema = (SchemaDetails)findDetails(schemaId);
        // if(schema==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Schema not found"), HttpStatus.NOT_FOUND);
        // schema.setId(updateSchemaRequest.getId());
        // schema.setSchemaData(updateSchemaRequest.getSchemaData());
        // return new ResponseEntity<>(HttpStatus.OK);
        if(this.updateRegistry(schemaId,updateSchemaRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Access Node not found"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ListSchemas200Response> listSchemas(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListSchemas200Response response = (ListSchemas200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListSchemas200Response(),
            new ListSchemas200ResponseLinks(),
            host+":"+port+"/v4/schemas?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "schemas/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSchema(String schemaId) {
        deleteFromId(schemaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}