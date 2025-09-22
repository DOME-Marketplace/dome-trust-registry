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
@Tag(name = "Trusted Access Node Registry", description = "Accepted Access Node addresses and their associations with public keys")
public interface AccessNodeRegistryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }


    @Operation(
        operationId = "getAccessNode",
        summary = "Get details of a specific registered Access Node",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Details of the Access Node", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AccessNodeDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Access Node has the requested address", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.GET,
        value = "/accessNodes/{accessNodeAddress}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<Object> getAccessNode(
        @Parameter(name = "accessNodeAddress", description = "The address of the access node to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("accessNodeAddress") String accessNodeAddress
    );


    @Operation(
        operationId = "registerAccessNode",
        summary = "Register a new Access Node associated with a public key",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Access Node registered successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/accessNodes",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<?> registerAccessNode(
        @Parameter(name = "AccessNodeDetails", description = "Access Node registration request", required = true)
        @Valid @RequestBody AccessNodeDetails accessNodeDetails 
    );


    @Operation(
        operationId = "listAccessNodes",
        summary = "List all registered Access Nodes",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of registered Access Nodes", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListAccessNodes200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/accessNodes",
        produces = { "application/json" }
    )
    abstract ResponseEntity<ListAccessNodes200Response> listAccessNodes(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    );

    
    @Operation(
        operationId = "updateAccessNode",
        summary = "Update an already registered Access Node",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Access Node updated successfully."),
            @ApiResponse(responseCode = "404", description = "No Access Node has the requested address", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/accessNodes/{accessNodeAddress}",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<?> updateAccessNode(
        @Parameter(name = "accessNodeAddress", description = "The address of the Access Node to update", required = true, in = ParameterIn.PATH)
        @PathVariable("accessNodeAddress") String did,
        @Parameter(name = "UpdateDidRequest", description = "Access Node update request", required = true)
        @Valid @RequestBody AccessNodeDetails updateDidRequest
    );


    @Operation(
        operationId = "deleteAccessNode",
        summary = "Deletes a specific registered Access Node",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The Access Node was deleted succesfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AccessNodeDetails.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.DELETE,
        value = "/accessNodes/{accessNodeAddress}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<?> deleteAccessNode(
        @Parameter(name = "accessNodeAddress", description = "The address of the access node to delete.", required = true, in = ParameterIn.PATH) @PathVariable("accessNodeAddress") String accessNodeAddress
    );
}
