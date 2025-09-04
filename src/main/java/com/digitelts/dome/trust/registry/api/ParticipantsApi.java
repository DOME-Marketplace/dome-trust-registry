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
@Tag(name = "Trusted Participants Registry", description = "Operations related to the Participants Registry")
public interface ParticipantsApi {

    default Optional<NativeWebRequest> getRequest() {
            return Optional.empty();
    }


    @Operation(operationId = "getParticipant", summary = "Get details of a specific participant", tags = {
                    "Trusted Participants Registry" }, responses = {
                                    @ApiResponse(responseCode = "200", description = "Participant details retrieved successfully.", content = {
                                                    @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDetails.class))
                                    }),
                                    @ApiResponse(responseCode = "404", description = "No participant has the requested ID",content = {
                                            @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
                                    })
                    })
    @RequestMapping(method = RequestMethod.GET, value = "/participants/{participantId}", produces = {
                    "application/json" })
    abstract ResponseEntity<Object> getParticipant(
                    @Parameter(name = "participantId", description = "The DID of the participant to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId);


    @Operation(operationId = "insertParticipant", summary = "Insert a new participant", tags = {
                    "Trusted Participants Registry" }, responses = {
                                    @ApiResponse(responseCode = "200", description = "Transaction executed successfully."),
                    })
    @RequestMapping(method = RequestMethod.POST, value = "/participants", consumes = { "application/json" })
    abstract ResponseEntity<WrongRequest> insertParticipant(
                    @Parameter(name = "InsertParticipantRequest", description = "", required = true) @Valid @RequestBody ParticipantDetails insertParticipantRequest);


    @Operation(operationId = "listParticipants", summary = "List all trusted participants", tags = {
                    "Trusted Participants Registry" }, responses = {
                                    @ApiResponse(responseCode = "200", description = "A list of trusted participants with pagination details.", content = {
                                                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListParticipants200Response.class))
                                    })
                    })
    @RequestMapping(method = RequestMethod.GET, value = "/participants", produces = { "application/json" })
    abstract ResponseEntity<ListParticipants200Response> listParticipants(
            @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
            @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize); 


    @Operation(operationId = "updateParticipant", summary = "Update an existing participant", tags = {
                    "Trusted Participants Registry" }, responses = {
                                    @ApiResponse(responseCode = "200", description = "Transaction built successfully."),
                                    @ApiResponse(responseCode = "400", description = "No participant has the requested ID", content = {
                                            @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
                                    })
                    })
    @RequestMapping(method = RequestMethod.PUT, value = "/participants/{participantId}", consumes = {
                    "application/json" })
    abstract ResponseEntity<WrongRequest> updateParticipant(
                    @Parameter(name = "participantId", description = "The DID of the participant to update.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId,
                    @Parameter(name = "UpdateParticipantRequest", description = "", required = true) @Valid @RequestBody ParticipantDetails updateParticipantRequest);


    @Operation(
        operationId = "deleteParticipant",
        summary = "Deletes a specific registered Participant",
        tags = { "Trusted Participants Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The Participant was deleted succesfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDetails.class))
            }),
            @ApiResponse(responseCode = "404", description = "No Participant has the requested ID", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
            })
        }
    )@RequestMapping(
        method = RequestMethod.DELETE,
        value = "/participants/{participantId}",
        produces = { "application/json" }
    )    
    abstract ResponseEntity<WrongRequest> deleteParticipant(
        @Parameter(name = "participantId", description = "The DID of the participant to delete.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId
    );
}
