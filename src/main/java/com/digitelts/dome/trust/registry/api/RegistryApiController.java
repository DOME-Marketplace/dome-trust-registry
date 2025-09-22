package com.digitelts.dome.trust.registry.api;

import java.util.*;
import javax.annotation.Nullable;
import com.digitelts.dome.trust.registry.model.*;

/**
 * Abstract class with common methods and attributes
 * @see AccessNodeRegistryApiController
 * @see LEARCredentialIssuerApiController
 * @see ParticipantsApiController
 * @see CredentialStatusRegistryApiController
 * @see SchemaRegistryApiController
 * @see ServiceRegistryApiController
 */
public abstract class RegistryApiController {
    
    protected List<TrustedRegistryDetails> detailsList;
    protected final String API_URL = "http://localhost:8080/v4/";


    public RegistryApiController(){
        detailsList = new ArrayList<>();
    }

    /**
     * Method to search instances of {@code Details} class by its DID
     * 
     * @param did The DID to look for
     * @return The {@code Details} class object whose DID matches the given DID, or
     * {@code null} if the given DID doesn't match with any from the list
     */
    @Nullable
    public TrustedRegistryDetails findDetails(String did){

        for(int i = 0; i < detailsList.size(); i++){
            TrustedRegistryDetails details = this.detailsList.get(i);
            if(details.getId().equals(did)) return details;
        }

        return null;
    }


    /**
     * Method to create an object of any {@code List200Response} subclass from the {@code Details}
     * list of the instance on which this method is invoked.
     * 
     * @param pageAfter
     * @param pageSize
     * @param response The object of any {@code List200Response} subclass being worked on
     * @param links The instance of any {@code List200ResponseLinks} subclass which will be included
     * in the returned object
     * @param urlString The base URL which will be formated to allow the user to move along the
     * list pages
     * @param apiUri The URI which will represent the exact subclass:
     * <ul>
     *  <li>{@code accessNodes}
     *  <li>{@code issuers}
     *  <li>{@code participants}
     * </ul>
     * @return An object of any {@code List200Response} subclass with the list made from the detail
     * list
     * 
     */
    public List200Response listDetails(Integer pageAfter, Integer pageSize, List200Response response, List200ResponseLinks links, String urlString, String apiUri){
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
            links.setFirst(String.format(urlString, 0, pageSize));
            links.setLast(String.format(urlString, 0, pageSize));
        }else{
            for(int i = 0; i < this.detailsList.size(); i++){
                TrustedRegistryDetails details = detailsList.get(i);
                summaries.add(details.getSummary(this.API_URL+apiUri));
            }
            paginated = summaries.subList(startIndex, endIndex);

            links.setFirst(String.format(urlString, 0, pageSize));
            links.setLast(String.format(urlString, (totalPages-1),pageSize));
            if((currentPage+1) < totalPages) links.setNext(String.format(urlString, (currentPage+1),pageSize));
            if(currentPage > 0) links.setPrev(String.format(urlString, (currentPage-1),pageSize));
        }

        response.setLinks(links);
        response.setPageSize(pageSize);
        response.setItems(paginated);

        return response;
    }

    /**
     * Method to remove the registry whose ID matches the given ID
     * 
     * @param did The ID of the registry to remove
     * @return {@code true} if the registry was removed o {@code false} otherwise
     */
    public boolean deleteFromId(String did){
        return this.detailsList.removeIf(detail -> detail.getId().equals(did));
    }
}
