package com.digitelts.dome.trust.registry.api;

import com.digitelts.dome.trust.registry.model.InsertIssuerRequest;
import com.digitelts.dome.trust.registry.model.IssuerDetails;
import com.digitelts.dome.trust.registry.model.ListIssuers200Response;
import com.digitelts.dome.trust.registry.model.UpdateIssuerRequest;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "Trusted Issuers Registry", description = "Operations related to the Issuers Registry")
public interface IssuersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /issuers/{issuerId} : Get details of a specific issuer
     *
     * @param issuerId The DID of the issuer to retrieve. (required)
     * @return Issuer details retrieved successfully. (status code 200)
     */
    @Operation(
        operationId = "getIssuer",
        summary = "Get details of a specific issuer",
        tags = { "Trusted Issuers Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Issuer details retrieved successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = IssuerDetails.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/issuers/{issuerId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<IssuerDetails> getIssuer(
        @Parameter(name = "issuerId", description = "The DID of the issuer to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"attributes\" : [ { \"rootTao\" : \"rootTao\", \"tao\" : \"tao\", \"issuerType\" : \"issuerType\", \"body\" : \"body\", \"hash\" : \"hash\" }], \"did\" : \"did\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /issuers/insert : Insert a new issuer
     * This method is restricted to RootTAOs, TAOs. This is a method extracted from EBSI&#39;s JSON RPC API. This method is not exactly the one from EBSI since this registry is a bit different. 
     *
     * @param insertIssuerRequest  (required)
     * @return Transaction executed successfully. (status code 200)
     */
    @Operation(
        operationId = "insertIssuer",
        summary = "Insert a new issuer",
        description = "This method is restricted to RootTAOs, TAOs. This is a method extracted from EBSI's JSON RPC API. This method is not exactly the one from EBSI since this registry is a bit different. ",
        tags = { "Trusted Issuers Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Transaction executed successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/issuers/insert",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> insertIssuer(
        @Parameter(name = "InsertIssuerRequest", description = "", required = true) @Valid @RequestBody InsertIssuerRequest insertIssuerRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /issuers : List all trusted issuers
     *
     * @param pageAfter Cursor for pagination (starting point) (optional)
     * @param pageSize Number of items per page (optional)
     * @return A list of trusted issuers with pagination details. (status code 200)
     */
    @Operation(
        operationId = "listIssuers",
        summary = "List all trusted issuers",
        tags = { "Trusted Issuers Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of trusted issuers with pagination details.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListIssuers200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/issuers",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ListIssuers200Response> listIssuers(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"total\" : 0, \"self\" : \"self\", \"pageSize\" : 6, \"links\" : { \"next\" : \"next\", \"last\" : \"last\", \"prev\" : \"prev\", \"first\" : \"first\" }, \"items\" : [ { \"href\" : \"href\", \"did\" : \"did\" }, { \"href\" : \"href\", \"did\" : \"did\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /issuers/{issuerId}/update : Update an existing issuer
     *
     * @param issuerId The DID of the issuer to update. (required)
     * @param updateIssuerRequest  (required)
     * @return Transaction built successfully. (status code 200)
     */
    @Operation(
        operationId = "updateIssuer",
        summary = "Update an existing issuer",
        tags = { "Trusted Issuers Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Transaction built successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/issuers/{issuerId}/update",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> updateIssuer(
        @Parameter(name = "issuerId", description = "The DID of the issuer to update.", required = true, in = ParameterIn.PATH) @PathVariable("issuerId") String issuerId,
        @Parameter(name = "UpdateIssuerRequest", description = "", required = true) @Valid @RequestBody UpdateIssuerRequest updateIssuerRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
