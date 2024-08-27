package com.digitelts.dome.trust.registry.api;

import com.digitelts.dome.trust.registry.model.InsertParticipantRequest;
import com.digitelts.dome.trust.registry.model.ListParticipants200Response;
import com.digitelts.dome.trust.registry.model.ParticipantDetails;
import com.digitelts.dome.trust.registry.model.UpdateParticipantRequest;
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
@Tag(name = "Trusted Participants Registry", description = "Operations related to the Participants Registry")
public interface ParticipantsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(
        operationId = "getParticipant",
        summary = "Get details of a specific participant",
        tags = { "Trusted Participants Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Participant details retrieved successfully.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDetails.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/participants/{participantId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ParticipantDetails> getParticipant(
        @Parameter(name = "participantId", description = "The DID of the participant to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"attributes\" : [ { \"participantType\" : \"participantType\", \"body\" : \"body\", \"hash\" : \"hash\" }], \"did\" : \"did\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @Operation(
        operationId = "insertParticipant",
        summary = "Insert a new participant",
        description = "This method is restricted to authorized registrars. This is a method adapted from EBSI's JSON RPC API. ",
        tags = { "Trusted Participants Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Transaction executed successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/participants",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> insertParticipant(
        @Parameter(name = "InsertParticipantRequest", description = "", required = true) @Valid @RequestBody InsertParticipantRequest insertParticipantRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @Operation(
        operationId = "listParticipants",
        summary = "List all trusted participants",
        tags = { "Trusted Participants Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of trusted participants with pagination details.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ListParticipants200Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/participants",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ListParticipants200Response> listParticipants(
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


    @Operation(
        operationId = "updateParticipant",
        summary = "Update an existing participant",
        tags = { "Trusted Participants Registry" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Transaction built successfully.")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/participants/{participantId}",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> updateParticipant(
        @Parameter(name = "participantId", description = "The DID of the participant to update.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId,
        @Parameter(name = "UpdateParticipantRequest", description = "", required = true) @Valid @RequestBody UpdateParticipantRequest updateParticipantRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
