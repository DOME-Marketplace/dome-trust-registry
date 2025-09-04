package com.digitelts.dome.trust.registry.api;

import java.util.*;
import javax.annotation.Nullable;
import com.digitelts.dome.trust.registry.model.*;

/**
 * Clase abstracta con métodos y atributos comunes
 * @see AccessNodeRegistryApiController
 * @see IssuersApiController
 * @see ParticipantsApiController
 */
public abstract class ApiController {
    
    protected List<TrustedRegistryDetails> detailsList;
    private final String API_URL = "http://localhost:8080/v4/";


    public ApiController(){
        detailsList = new ArrayList<>();
    }

    /**
     * Método para buscar instancias de clase Details por su DID
     * 
     * @param did El DID del objeto a buscar
     * @return El objeto de clase Details cuyo DID coincide con el DID
     * pasado como parámetro, o null si el DID no coincide con ninguno de
     * la lista
     */
    @Nullable
    public TrustedRegistryDetails findDetails(String did){

        for(int i = 0; i < detailsList.size(); i++){
            TrustedRegistryDetails details = this.detailsList.get(i);
            if(details.getDid().equals(did)) return details;
        }

        return null;
    }


    /**
     * Método para generar un objeto de alguna subclase de List200Response a partir
     * de la lista de Details de la instancia sobre la que se invoca este método
     * 
     * @param pageAfter
     * @param pageSize
     * @param response El objeto de alguna subclase de List200Response sobre el que se
     * va a trabajar
     * @param links El objeto de alguna subclase de List200ResponseLinks que se va a incluir
     * en el objeto devuelto
     * @param urlString La URL base que se formateará para poder moverse entre las páginas
     * de la lista 
     * @param apiUri La URI que representa a la subclase concreta:
     *  - accessNodes
     *  - issuers
     *  - participants
     * @return Un objeto de alguna subclase de List200Response con la lista generada a partir
     * de los datos de la lista de detalles
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

        for(int i = 0; i < this.detailsList.size(); i++){
            TrustedRegistryDetails details = detailsList.get(i);
            summaries.add(details.getSummary(this.API_URL+apiUri));
        }
        paginated = summaries.subList(startIndex, endIndex);

        links.setFirst(String.format(urlString, 0, pageSize));
        links.setLast(String.format(urlString, (totalPages-1),pageSize));
        if((currentPage+1) < totalPages) links.setNext(String.format(urlString, (currentPage+1),pageSize));
        if(currentPage > 0) links.setPrev(String.format(urlString, (currentPage-1),pageSize));

        response.setLinks(links);
        response.setPageSize(pageSize);
        response.setItems(paginated);

        return response;
    }
}
