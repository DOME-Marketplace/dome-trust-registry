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
    private final String urlString = "http://localhost:8080/v4/credentialStatuses?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d";
    private final String apiUri = "credentialStatuses/";

    public CredentialStatusRegistryApiController(NativeWebRequest request, SchemaRegistryApiController schemaRegistryApiController) {
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
    public ResponseEntity<WrongRequest> registerCredentialStatus(@Valid CredentialStatusDetails credentialStatusDetails) {
        if(this.detailsList.contains(credentialStatusDetails)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Invalid Credential Status"),HttpStatus.BAD_REQUEST);
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
            this.urlString,
            this.apiUri                  
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WrongRequest> deleteCredentialStatus(String credentialStatusId) {
        if(deleteFromId(credentialStatusId)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Credential Status not found"),HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<ListCredentialStatuses200Response> listInvalidCredentialStatuses(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        ListCredentialStatuses200Response response = new ListCredentialStatuses200Response();
        ListCredentialStatuses200ResponseLinks links = new ListCredentialStatuses200ResponseLinks();
        List<TrustedRegistrySummary> summaries = new ArrayList<>(), paginated = new ArrayList<>();

        if(pageSize == null || pageSize <= 0){
            pageSize = 10;
        }
        int currentPage = (pageAfter != null) ? pageAfter : 0;
        int totalItems = this.detailsList.size();
        int totalPages = (int) Math.ceil((double) totalItems/pageSize);
        int startIndex = currentPage*pageSize;
        int endIndex = Math.min(startIndex+pageSize, this.detailsList.size());

        if(this.detailsList.isEmpty()){
            links.setFirst(String.format(this.urlString, 0, pageSize));
            links.setLast(String.format(this.urlString, 0, pageSize));
        }else{
            for(int i = 0; i < this.detailsList.size(); i++){
                CredentialStatusDetails details = (CredentialStatusDetails) detailsList.get(i);
                if(!details.getValidity()) summaries.add(details.getSummary(this.API_URL+this.apiUri));
            }
            paginated = summaries.subList(startIndex, endIndex);

            links.setFirst(String.format(this.urlString, 0, pageSize));
            links.setLast(String.format(this.urlString, (totalPages-1),pageSize));
            if((currentPage+1) < totalPages) links.setNext(String.format(this.urlString, (currentPage+1),pageSize));
            if(currentPage > 0) links.setPrev(String.format(this.urlString, (currentPage-1),pageSize));
        }

        response.setLinks(links);
        response.setPageSize(pageSize);
        response.setItems(paginated);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListCredentialStatuses200Response> listRevokedCredentialStatuses(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        ListCredentialStatuses200Response response = new ListCredentialStatuses200Response();
        ListCredentialStatuses200ResponseLinks links = new ListCredentialStatuses200ResponseLinks();
        List<TrustedRegistrySummary> summaries = new ArrayList<>(), paginated = new ArrayList<>();

        if(pageSize == null || pageSize <= 0){
            pageSize = 10;
        }
        int currentPage = (pageAfter != null) ? pageAfter : 0;
        int totalItems = this.detailsList.size();
        int totalPages = (int) Math.ceil((double) totalItems/pageSize);
        int startIndex = currentPage*pageSize;
        int endIndex = Math.min(startIndex+pageSize, this.detailsList.size());

        if(this.detailsList.isEmpty()){
            links.setFirst(String.format(this.urlString, 0, pageSize));
            links.setLast(String.format(this.urlString, 0, pageSize));
        }else{
            for(int i = 0; i < this.detailsList.size(); i++){
                CredentialStatusDetails details = (CredentialStatusDetails) detailsList.get(i);
                if(!details.getRevocation()) summaries.add(details.getSummary(this.API_URL+this.apiUri));
            }
            paginated = summaries.subList(startIndex, endIndex);

            links.setFirst(String.format(this.urlString, 0, pageSize));
            links.setLast(String.format(this.urlString, (totalPages-1),pageSize));
            if((currentPage+1) < totalPages) links.setNext(String.format(this.urlString, (currentPage+1),pageSize));
            if(currentPage > 0) links.setPrev(String.format(this.urlString, (currentPage-1),pageSize));
        }

        response.setLinks(links);
        response.setPageSize(pageSize);
        response.setItems(paginated);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}