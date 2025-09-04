package com.digitelts.dome.trust.registry.api;

import java.util.*;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.model.*;
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
                    }),
                    @ApiResponse(responseCode = "404", description = "No issuer has the ID requested", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
                    })
            })
    @RequestMapping(method = RequestMethod.GET, value = "/issuers/{issuerId}", produces = { "application/json" })
    abstract ResponseEntity<Object> getIssuer(
            @Parameter(name = "issuerId", description = "The DID of the issuer to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId);


    @Operation(operationId = "insertIssuer", summary = "Insert a new issuer", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction executed successfully.")
            })
    @RequestMapping(method = RequestMethod.POST, value = "/issuers", consumes = { "application/json" })
    abstract ResponseEntity<WrongRequest> insertIssuer(
            @Parameter(name = "InsertIssuerRequest", description = "", required = true) @Valid @RequestBody IssuerDetails insertIssuerRequest);


    @Operation(operationId = "listIssuers", summary = "List all trusted issuers", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "A list of trusted issuers with pagination details.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListIssuers200Response.class))
                    })
            })
    @RequestMapping(method = RequestMethod.GET, value = "/issuers", produces = { "application/json" })
    abstract ResponseEntity<ListIssuers200Response> listIssuers(
            @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
            @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize);


    @Operation(operationId = "updateIssuer", summary = "Update an existing issuer", tags = {
            "Trusted Issuers Registry" }, responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction built successfully.")
            })
    @RequestMapping(method = RequestMethod.PUT, value = "/issuers/{issuerId}", consumes = { "application/json" })
    abstract ResponseEntity<WrongRequest> updateIssuer(
            @Parameter(name = "issuerId", description = "The DID of the issuer to update.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId,
            @Parameter(name = "UpdateIssuerRequest", description = "", required = true) @Valid @RequestBody IssuerDetails updateIssuerRequest);


    @Operation(
        operationId = "deleteIssuer",
        summary = "Deletes a specific registered Issuer",
        tags = { "Trusted Issuers Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The Issuer was deleted succesfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = IssuerDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Issuer has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.DELETE,
        value = "/issuers/{issuerId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<WrongRequest> deleteIssuer(
        @Parameter(name = "issuerId", description = "The DID of the issuer to delete.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId
    );
}
