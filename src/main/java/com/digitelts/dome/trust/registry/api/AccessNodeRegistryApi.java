package com.digitelts.dome.trust.registry.api;

import com.digitelts.dome.trust.registry.model.AccessNodeDetails;
import com.digitelts.dome.trust.registry.model.IssuerDetails;
import com.digitelts.dome.trust.registry.model.ListAccessNodes200Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.web.context.request.NativeWebRequest;

@Validated
@Tag(name = "Trusted Access Node Registry", description = "Accepted Access Node DIDs and their associations with public keys")
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
            })
        }
    )@RequestMapping(
        method = RequestMethod.GET,
        value = "/accessNodes/{accessNodeId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<AccessNodeDetails> getAccessNode(
        @Parameter(name = "accessNodeId", description = "The DID of the access node to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("accessNodeId") String accessNodeId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
        operationId = "registerDid",
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
    default ResponseEntity<Void> registerDid(
        @Parameter(name = "AccessNodeDetails", description = "DID registration request", required = true)
        @Valid @RequestBody AccessNodeDetails accessNodeDetails 
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
        operationId = "listDids",
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
    default ResponseEntity<ListAccessNodes200Response> listDids(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(
        operationId = "updateDid",
        summary = "Update an already registered Access Node",
        tags = { "Trusted Access Node Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Access Node updated successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/accessNodes/{accessNodeId}",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateDid(
        @Parameter(name = "did", description = "The DID of the Access Node to update", required = true, in = ParameterIn.PATH)
        @PathVariable("did") String did,
        @Parameter(name = "UpdateDidRequest", description = "DID update request", required = true)
        @Valid @RequestBody AccessNodeDetails updateDidRequest
        
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
