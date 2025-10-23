package com.digitelts.dome.trust.registry.api;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.model.*;
import com.digitelts.dome.trust.registry.repositories.ServiceRepository;

import java.util.*;
import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class ServiceRegistryApiController extends RegistryApiController<ServiceDetails> implements ServiceRegistryApi {

    private final NativeWebRequest request;

    public ServiceRegistryApiController(NativeWebRequest request, ServiceRepository repo) {
        super(repo);
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getService(String clientId) {
        ServiceDetails registry = (ServiceDetails)findDetails(clientId);
        if(registry==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Service not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(registry,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> registerService(@Valid ServiceDetails serviceDetails) {
        if(this.insertRegistry(serviceDetails)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Access Node already exists"),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<WrongRequest> updateService(String clientId, @Valid ServiceDetails updateServiceRequest) {
        if(this.updateRegistry(clientId,updateServiceRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Access Node not found"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ListServices200Response> listServices(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListServices200Response response = (ListServices200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListServices200Response(),
            new ListServices200ResponseLinks(),
            this.API_URL+"services?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "services/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}