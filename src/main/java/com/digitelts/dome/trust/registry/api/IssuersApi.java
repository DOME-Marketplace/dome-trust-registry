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
            @RequestParam(value = "pageAfter", required = false) Integer pageAfter,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageBefore", required = false) Integer pageBefore,
            @RequestParam(value = "first", required = false) Boolean first,
            @RequestParam(value = "last", required = false) Boolean last) {

        // If a valid pageSize is not specified, we assign 10 by default
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        // Create the list of fictitious senders
        List<IssuerSummary> allIssuers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            allIssuers.add(new IssuerSummary("did" + i, "href" + i));
        }

        // Create fictitious emitter outside the loop
        allIssuers.add(new IssuerSummary("did Fprueba", "href FHprueba"));

        int totalItems = allIssuers.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Define the current page based on the parameters
        int currentPage = 0;

        if (Boolean.TRUE.equals(first)) {
            // Go to the first page if 'first' is present
            currentPage = 0;
        } else if (Boolean.TRUE.equals(last)) {
            // Go to last page if 'last' is present
            currentPage = totalPages - 1;
        } else if (pageBefore != null) {
            // If pageBefore is specified, we calculate the previous page
            currentPage = (pageBefore > 0) ? pageBefore - 1 : 0;
        } else if (pageAfter != null) {
            // If pageAfter is specified, we advance to the next page
            currentPage = pageAfter;
        }

        // Calculate start and end indexes for paginated sublist
        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allIssuers.size());

        // Sublist paged issuers
        List<IssuerSummary> paginatedIssuers = allIssuers.subList(startIndex, endIndex);

        // Response construction
        ListIssuers200Response response = new ListIssuers200Response();
        ListIssuers200ResponseLinks links = new ListIssuers200ResponseLinks();

        // Generate links for pagination
        links.setFirst("http://localhost:8080/v4/issuers?first=true&pageSize=" + pageSize);

        // Link to next page
        links.setNext((currentPage + 1) < totalPages
                ? "http://localhost:8080/v4/issuers?pageAfter=" + (currentPage + 1) + "&pageSize=" + pageSize
                : null);

        // Link to previous page
        links.setPrev(currentPage > 0
                ? "http://localhost:8080/v4/issuers?pageBefore=" + currentPage + "&pageSize=" + pageSize
                : null);

        // Link to last page
        links.setLast("http://localhost:8080/v4/issuers?last=true&pageSize=&pageSize=" + pageSize);

        // Assign links and results to the response
        response.setLinks(links);
        response.setTotal(totalItems);
        response.setPageSize(pageSize);
        response.setItems(paginatedIssuers);

        // Return response with HTTP 200 OK status
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
