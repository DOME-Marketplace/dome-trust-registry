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
@Tag(name = "Trusted Schemas Registry", description = "Operations related to the Schemas Registry")
public interface SchemaRegistryApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }


     @Operation(
        operationId = "getSchema",
        summary = "Get details of a specific registered Schema",
        tags = { "Trusted Schemas Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Details of the Schema", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SchemaDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Schema has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.GET,
        value = "/schemas/{schemaId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<Object> getSchema(
        @Parameter(name = "schemaId", description = "The ID of the Schema to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("schemaId") String schemaId
    );


    @Operation(
        operationId = "registerSchema",
        summary = "Register a new Schema associated with a public key",
        tags = { "Trusted Schemas Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Schema registered successfully.", content=@Content),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            }),
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/schemas",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<?> registerSchema(
        @Parameter(name = "SchemaDetails", description = "ID registration request", required = true)
        @Valid @RequestBody SchemaDetails SchemaDetails,
        @RequestHeader("Authorization") String bearerToken
    );


    @Operation(
        operationId = "listSchemas",
        summary = "List all registered Schemas",
        tags = { "Trusted Schemas Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of registered Schemas", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListSchemas200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/schemas",
        produces = { "application/json" }
    )
    abstract ResponseEntity<ListSchemas200Response> listSchemas(
        @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
        @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
        @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
    );

    
    @Operation(
        operationId = "updateSchema",
        summary = "Update an already registered Schema",
        tags = { "Trusted Schemas Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Schema updated successfully.", content=@Content),
            @ApiResponse(responseCode = "404", description = "No Schema has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            }),
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/schemas/{schemaId}",
        consumes = { "application/json" }
    )
    abstract ResponseEntity<?> updateSchema(
        @Parameter(name = "schemaId", description = "The ID of the Schema to update", required = true, in = ParameterIn.PATH)
        @PathVariable("schemaId") String did,
        @Parameter(name = "UpdateSchemaRequest", description = "Schema update request", required = true)
        @Valid @RequestBody SchemaDetails updateSchemaRequest,
        @RequestHeader("Authorization") String bearerToken
    );


    @Operation(
        operationId = "deleteSchema",
        summary = "Deletes a specific registered Schema",
        tags = { "Trusted Schemas Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The Schema was deleted succesfully", content=@Content),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            }),
        }
    )@RequestMapping(
        method = RequestMethod.DELETE,
        value = "/schemas/{schemaId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<?> deleteSchema(
        @Parameter(name = "schemaId", description = "The ID of the schema to delete.", required = true, in = ParameterIn.PATH) @PathVariable("schemaId") String schemaId,
        @RequestHeader("Authorization") String bearerToken
    );
}
