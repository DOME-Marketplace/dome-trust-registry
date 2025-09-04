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
@Tag(name = "Trusted Service Registry", description = "Accepted Service DIDs and their associations with public keys")
public interface ServiceRegistryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }


     @Operation(
        operationId = "getService",
        summary = "Get details of a specific registered Service",
        tags = { "Trusted Service Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Details of the Service", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Service has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.GET,
        value = "/services/{serviceId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<Object> getService(
        @Parameter(name = "ServiceId", description = "The DID of the Service to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("ServiceId") String ServiceId
    );


    @Operation(
        operationId = "registerService",
        summary = "Register a new Service associated with a public key",
        tags = { "Trusted Service Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Service registered successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/services",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<Void> registerService(
        @Parameter(name = "ServiceDetails", description = "DID registration request", required = true)
        @Valid @RequestBody ServiceDetails ServiceDetails 
    );


    @Operation(
        operationId = "listServices",
        summary = "List all registered Services",
        tags = { "Trusted Service Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of registered Services", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListServices200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/services",
        produces = { "application/json" }
    )
    abstract ResponseEntity<ListServices200Response> listServices(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    );

    
    @Operation(
        operationId = "updateService",
        summary = "Update an already registered Service",
        tags = { "Trusted Service Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Service updated successfully."),
            @ApiResponse(responseCode = "404", description = "No Service has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/services/{serviceId}",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<WrongRequest> updateService(
        @Parameter(name = "ServiceId", description = "The DID of the Service to update", required = true, in = ParameterIn.PATH)
        @PathVariable("ServiceId") String did,
        @Parameter(name = "UpdateDidRequest", description = "DID update request", required = true)
        @Valid @RequestBody ServiceDetails updateDidRequest
    );
}
