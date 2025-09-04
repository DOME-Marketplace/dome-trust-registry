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
public class ServiceRegistryApiController extends ApiController implements ServiceRegistryApi {

    private final NativeWebRequest request;

    public ServiceRegistryApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getService(String serviceId) {
        ServiceDetails node = (ServiceDetails)findDetails(serviceId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Service not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(node,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> registerService(@Valid ServiceDetails serviceDetails) {
        if(this.detailsList.contains(serviceDetails)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Invalid Service"),HttpStatus.BAD_REQUEST);
        this.detailsList.add(serviceDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> updateService(String serviceId, @Valid ServiceDetails updateDidRequest) {
        ServiceDetails node = (ServiceDetails)findDetails(serviceId);
        if(node==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Service not found"), HttpStatus.NOT_FOUND);
        node.setDid(updateDidRequest.getDid());
        node.setValidFrom(updateDidRequest.getValidFrom());
        node.setValidTo(updateDidRequest.getValidTo());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListServices200Response> listServices(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        
        ListServices200Response response = (ListServices200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListServices200Response(),
            new ListServices200ResponseLinks(),
            "http://localhost:8080/v4/services?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "services/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> deleteService(String serviceId) {
        if(deleteFromId(serviceId)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Service not found"),HttpStatus.NOT_FOUND);
    }
}