package com.digitelts.dome.trust.registry.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.digitelts.dome.trust.registry.model.EntitySummary;
import com.digitelts.dome.trust.registry.model.PaginateResponse;
import com.digitelts.dome.trust.registry.model.ResponseLinks;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "Registry", description = "Operations related to the Issuers and Participants Registry")
public interface UnifiedApi {
     default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(operationId = "listIssuers", summary = "List all trusted issuers", tags = {
            "Registry" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A list of trusted issuers with pagination details.")
            })
    @RequestMapping(method = RequestMethod.GET, value = "/issuers", produces = { "application/json" })
    default ResponseEntity<PaginateResponse> listIssuers(
            @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
            @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize) {

        return paginateIssuers(pageAfter, pageSize);
    }

    @Operation(operationId = "listParticipants", summary = "List all trusted participants", tags = {
            "Registry" }, responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "A list of trusted participants with pagination details.")
            })
    @RequestMapping(method = RequestMethod.GET, value = "/participants", produces = { "application/json" })
    default ResponseEntity<PaginateResponse> listParticipants(
            @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
            @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize) {

        return paginateParticipants(pageAfter, pageSize);
    }

    default ResponseEntity<PaginateResponse> paginateIssuers(Integer pageAfter, Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        int currentPage = (pageAfter != null) ? pageAfter : 0;

        List<EntitySummary> allIssuers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            allIssuers.add(new EntitySummary("did" + i, "href" + i));
        }

        allIssuers.add(new EntitySummary("IsseurEnd", "IsseurEnd"));

        int totalItems = allIssuers.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);

        List<EntitySummary> paginatedIssuers = allIssuers.subList(startIndex, endIndex);

        PaginateResponse response = new PaginateResponse();
        response.setItems(paginatedIssuers);
        response.setPageSize(pageSize);
        response.setTotal(totalItems);

        ResponseLinks links = new ResponseLinks();
        links.setFirst("http://localhost:8080/v4/issuers?page%5Bafter%5D=0&page%5Bsize%5D=" + pageSize);
        links.setNext((currentPage + 1) < totalPages
                ? "http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (currentPage + 1) + "&page%5Bsize%5D=" + pageSize
                : null);
        links.setPrev(currentPage > 0
                ? "http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (currentPage - 1) + "&page%5Bsize%5D=" + pageSize
                : null);
        links.setLast("http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (totalPages - 1) + "&page%5Bsize%5D=" + pageSize);

        response.setLinks(links);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    default ResponseEntity<PaginateResponse> paginateParticipants(Integer pageAfter, Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        int currentPage = (pageAfter != null) ? pageAfter : 0;

        List<EntitySummary> allParticipants = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            allParticipants.add(new EntitySummary("did" + i, "href" + i));
        }

        allParticipants.add(new EntitySummary("ParticipantEnd", "ParticipantEnd"));

        int totalItems = allParticipants.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);

        List<EntitySummary> paginatedParticipants = allParticipants.subList(startIndex, endIndex);

        PaginateResponse response = new PaginateResponse();
        response.setItems(paginatedParticipants);
        response.setPageSize(pageSize);
        response.setTotal(totalItems);

        ResponseLinks links = new ResponseLinks();
        links.setFirst("http://localhost:8080/v4/participants?page%5Bafter%5D=0&page%5Bsize%5D=" + pageSize);
        links.setNext((currentPage + 1) < totalPages
                ? "http://localhost:8080/v4/participants?page%5Bafter%5D=" + (currentPage + 1) + "&page%5Bsize%5D=" + pageSize
                : null);
        links.setPrev(currentPage > 0
                ? "http://localhost:8080/v4/participants?page%5Bafter%5D=" + (currentPage - 1) + "&page%5Bsize%5D=" + pageSize
                : null);
        links.setLast("http://localhost:8080/v4/participants?page%5Bafter%5D=" + (totalPages - 1) + "&page%5Bsize%5D=" + pageSize);

        response.setLinks(links);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
