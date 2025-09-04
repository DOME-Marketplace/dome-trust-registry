package com.digitelts.dome.trust.registry.api;

import com.digitelts.dome.trust.registry.model.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;
import org.springframework.web.context.request.NativeWebRequest;

@Validated
@Tag(name = "Trusted Credential Statuses Registry", description = "Operations related to the Credential Statuses Registry")
public interface CredentialStatusRegistryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }


     @Operation(
        operationId = "getCredentialStatus",
        summary = "Get details of a specific registered Credential Status",
        tags = { "Trusted Credential Statuses Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Details of the Credential Status", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CredentialStatusDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Credential Status has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.GET,
        value = "/credentialStatuses/{credentialStatusId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<Object> getCredentialStatus(
        @Parameter(name = "CredentialStatusId", description = "The DID of the Credential Status to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("CredentialStatusId") String CredentialStatusId
    );


    @Operation(
        operationId = "registerCredentialStatus",
        summary = "Register a new Credential Status associated with a public key",
        tags = { "Trusted Credential Statuses Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Credential Status registered successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/credentialStatuses",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<WrongRequest> registerCredentialStatus(
        @Parameter(name = "CredentialStatusDetails", description = "DID registration request", required = true)
        @Valid @RequestBody CredentialStatusDetails CredentialStatusDetails 
    );


    @Operation(
        operationId = "listCredentialStatuss",
        summary = "List all registered Credential Statuses",
        tags = { "Trusted Credential Statuses Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of registered Credential Statuses", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListCredentialStatuses200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/credentialStatuses",
        produces = { "application/json" }
    )
    abstract ResponseEntity<ListCredentialStatuses200Response> listCredentialStatuses(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    );

    
    @Operation(
        operationId = "updateCredentialStatus",
        summary = "Update an already registered Credential Status",
        tags = { "Trusted Credential Statuses Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Credential Status updated successfully."),
            @ApiResponse(responseCode = "404", description = "No Credential Status has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/credentialStatuses/{credentialStatusId}",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<WrongRequest> updateCredentialStatus(
        @Parameter(name = "CredentialStatusId", description = "The DID of the Credential Status to update", required = true, in = ParameterIn.PATH)
        @PathVariable("CredentialStatusId") String did,
        @Parameter(name = "UpdateDidRequest", description = "DID update request", required = true)
        @Valid @RequestBody CredentialStatusDetails updateDidRequest
    );


    @Operation(
        operationId = "deleteCredentialStatus",
        summary = "Deletes a specific registered Credential Status",
        tags = { "Trusted Credential Statuses Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The Credential Status was deleted succesfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CredentialStatusDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Credential Status has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.DELETE,
        value = "/credentialStatuses/{credentialStatusId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<WrongRequest> deleteCredentialStatus(
        @Parameter(name = "credentialStatusId", description = "The DID of the credential status to delete.", required = true, in = ParameterIn.PATH) @PathVariable("credentialStatusId") String credentialStatusId
    );
}
