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

import com.digitelts.dome.trust.registry.model.ListParticipants200Response;
import com.digitelts.dome.trust.registry.model.ListParticipants200ResponseLinks;
import com.digitelts.dome.trust.registry.model.ParticipantDetails;
import com.digitelts.dome.trust.registry.model.ParticipantSummary;

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
                                        })
                        })
        @RequestMapping(method = RequestMethod.GET, value = "/participants/{participantId}", produces = {
                        "application/json" })

        default ResponseEntity<ParticipantDetails> getParticipant(
                        @Parameter(name = "participantId", description = "The DID of the participant to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId) {
                getRequest().ifPresent(request -> {
                        for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                                        String exampleString = "{ \"attributes\" : [ { \"participantType\" : \"participantType\", \"body\" : \"body\", \"hash\" : \"hash\" }], \"did\" : \"did\" }";
                                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                                        break;
                                }
                        }
                });
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        }

        @Operation(operationId = "insertParticipant", summary = "Insert a new participant", tags = {
                        "Trusted Participants Registry" }, responses = {
                                        @ApiResponse(responseCode = "200", description = "Transaction executed successfully.")
                        })
        @RequestMapping(method = RequestMethod.POST, value = "/participants", consumes = { "application/json" })

        default ResponseEntity<Void> insertParticipant(
                        @Parameter(name = "InsertParticipantRequest", description = "", required = true) @Valid @RequestBody ParticipantDetails insertParticipantRequest) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        }

        @Operation(operationId = "listParticipants", summary = "List all trusted participants", tags = {
                        "Trusted Participants Registry" }, responses = {
                                        @ApiResponse(responseCode = "200", description = "A list of trusted participants with pagination details.", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ListParticipants200Response.class))
                                        })
                        })
        @RequestMapping(method = RequestMethod.GET, value = "/participants", produces = { "application/json" })

        default ResponseEntity<ListParticipants200Response> listParticipants(
                        @RequestParam(value = "pageAfter", required = false) Integer pageAfter,
                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                        @RequestParam(value = "pageBefore", required = false) Integer pageBefore,
                        @RequestParam(value = "first", required = false) Boolean first,
                        @RequestParam(value = "last", required = false) Boolean last) {

                // Set default page size if not provided or invalid
                if (pageSize == null || pageSize <= 0) {
                        pageSize = 10;
                }

                // Simulation of a fictitious list of participants
                List<ParticipantSummary> allParticipants = new ArrayList<>();
                for (int i = 1; i <= 40; i++) {
                        allParticipants.add(new ParticipantSummary("did" + i, "href" + i));
                }

                // Create fictitious emitter outside the loop
                allParticipants.add(new ParticipantSummary("did Fprueba", "href FHprueba"));

                int totalItems = allParticipants.size();
                int totalPages = (int) Math.ceil((double) totalItems / pageSize);

                // Determine the current page
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

                // Calculate start and end indexes for pagination
                int startIndex = currentPage * pageSize;
                int endIndex = Math.min(startIndex + pageSize, allParticipants.size());

                // Obtain the sub-list of participants to be returned
                List<ParticipantSummary> paginatedParticipants = allParticipants.subList(startIndex, endIndex);

                // Building the paging response
                ListParticipants200Response response = new ListParticipants200Response();
                ListParticipants200ResponseLinks links = new ListParticipants200ResponseLinks();

                // Link to first page
                links.setFirst("http://localhost:8080/v4/participants?first=true&pageSize=" + pageSize);

                // Link to next page
                links.setNext((currentPage + 1) < totalPages
                                ? "http://localhost:8080/v4/participants?pageAfter=" + (currentPage + 1)
                                                + "&pageSize=" + pageSize
                                : null);

                // Link to previous page (only if we are not on the first page)
                links.setPrev(currentPage > 0
                                ? "http://localhost:8080/v4/participants?pageBefore=" + currentPage + "&pageSize="
                                                + pageSize
                                : null);

                // Link to last page
                links.setLast("http://localhost:8080/v4/participants?last=true&pageSize=" + pageSize);

                // Assign the links, total quantity and paged elements to the
                // reply
                response.setLinks(links);
                response.setTotal(totalItems);
                response.setPageSize(pageSize);
                response.setItems(paginatedParticipants);

                // Return response with HTTP 200 OK status
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @Operation(operationId = "updateParticipant", summary = "Update an existing participant", tags = {
                        "Trusted Participants Registry" }, responses = {
                                        @ApiResponse(responseCode = "200", description = "Transaction built successfully.")
                        })
        @RequestMapping(method = RequestMethod.PUT, value = "/participants/{participantId}", consumes = {
                        "application/json" })

        default ResponseEntity<Void> updateParticipant(
                        @Parameter(name = "participantId", description = "The DID of the participant to update.", required = true, in = ParameterIn.PATH) @PathVariable("participantId") String participantId,
                        @Parameter(name = "UpdateParticipantRequest", description = "", required = true) @Valid @RequestBody ParticipantDetails updateParticipantRequest) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        }

}
