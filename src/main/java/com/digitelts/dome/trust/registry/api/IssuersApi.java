package com.digitelts.dome.trust.registry.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import com.digitelts.dome.trust.registry.model.IssuerDetails;
import com.digitelts.dome.trust.registry.model.IssuerSummary;
import com.digitelts.dome.trust.registry.model.ListIssuers200Response;
import com.digitelts.dome.trust.registry.model.ListIssuers200ResponseLinks;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "Trusted Issuers Registry", description = "Operations related to the Issuers Registry")
public interface IssuersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(operationId = "getIssuer", summary = "Get details of a specific issuer", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Issuer details retrieved successfully.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = IssuerDetails.class))
                    })
            })
    @RequestMapping(method = RequestMethod.GET, value = "/issuers/{issuerId}", produces = { "application/json" })

    default ResponseEntity<IssuerDetails> getIssuer(
            @Parameter(name = "issuerId", description = "The DID of the issuer to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"attributes\" : [ { \"rootTao\" : \"rootTao\", \"tao\" : \"tao\", \"issuerType\" : \"issuerType\", \"body\" : \"body\", \"hash\" : \"hash\" }], \"did\" : \"did\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Operation(operationId = "insertIssuer", summary = "Insert a new issuer", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction executed successfully.")
            })
    @RequestMapping(method = RequestMethod.POST, value = "/issuers", consumes = { "application/json" })

    default ResponseEntity<Void> insertIssuer(
            @Parameter(name = "InsertIssuerRequest", description = "", required = true) @Valid @RequestBody IssuerDetails insertIssuerRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Operation(operationId = "listIssuers", summary = "List all trusted issuers", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "A list of trusted issuers with pagination details.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListIssuers200Response.class))
                    })
            })
    @RequestMapping(method = RequestMethod.GET, value = "/issuers", produces = { "application/json" })

    default ResponseEntity<ListIssuers200Response> listIssuers(
            @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
            @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize) {

        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        int currentPage = (pageAfter != null) ? pageAfter : 0;

        List<IssuerSummary> allIssuers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            allIssuers.add(new IssuerSummary("did" + i, "href" + i));
        }

        allIssuers.add(new IssuerSummary("didPrueba", "hrefPrueba"));

        int totalItems = allIssuers.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allIssuers.size());

        List<IssuerSummary> paginatedIssuers = allIssuers.subList(startIndex, endIndex);

        ListIssuers200Response response = new ListIssuers200Response();

        ListIssuers200ResponseLinks links = new ListIssuers200ResponseLinks();

        links.setFirst("http://localhost:8080/v4/issuers?page%5Bafter%5D=0&page%5Bsize%5D=" + pageSize);

        links.setNext((currentPage + 1) < totalPages
                ? "http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (currentPage + 1)
                        + "&page%5Bsize%5D=" + pageSize
                : null);

        links.setPrev(currentPage > 0
                ? "http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (currentPage - 1)
                        + "&page%5Bsize%5D=" + pageSize
                : null);

        links.setLast("http://localhost:8080/v4/issuers?page%5Bafter%5D=" + (totalPages - 1)
                + "&page%5Bsize%5D=" + pageSize);

        response.setLinks(links);

        response.setTotal(totalItems);
        response.setPageSize(pageSize);
        response.setItems(paginatedIssuers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(operationId = "updateIssuer", summary = "Update an existing issuer", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction built successfully.")
            })
    @RequestMapping(method = RequestMethod.PUT, value = "/issuers/{issuerId}", consumes = { "application/json" })

    default ResponseEntity<Void> updateIssuer(
            @Parameter(name = "issuerId", description = "The DID of the issuer to update.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId,
            @Parameter(name = "UpdateIssuerRequest", description = "", required = true) @Valid @RequestBody IssuerDetails updateIssuerRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
