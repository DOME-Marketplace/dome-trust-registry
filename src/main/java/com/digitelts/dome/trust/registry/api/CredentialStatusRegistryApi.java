// package com.digitelts.dome.trust.registry.api;

// import com.digitelts.dome.trust.registry.model.*;
// import io.swagger.v3.oas.annotations.*;
// import io.swagger.v3.oas.annotations.media.*;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import io.swagger.v3.oas.annotations.enums.ParameterIn;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.*;
// import javax.validation.Valid;
// import java.util.Optional;
// import org.springframework.web.context.request.NativeWebRequest;

// @Validated
// @Tag(name = "Trusted Credential Statuses Registry", description = "Operations related to the Credential Statuses Registry")
// public interface CredentialStatusRegistryApi {

//     default Optional<NativeWebRequest> getRequest() {
//         return Optional.empty();
//     }


//      @Operation(
//         operationId = "getCredentialStatus",
//         summary = "Get details of a specific registered Credential Status",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "Details of the Credential Status", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = CredentialStatusDetails.class))
//             }),
//             @ApiResponse(responseCode = "404", description = "No Credential Status has the requested ID", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
//             })
//         }
//     )@RequestMapping(
//         method = RequestMethod.GET,
//         value = "/credentialStatuses/{credentialStatusId}",
//         produces = { "application/json" }
//     )    
//     abstract ResponseEntity<Object> getCredentialStatus(
//         @Parameter(name = "CredentialStatusId", description = "The ID of the Credential Status to retrieve.", required = true, in = ParameterIn.PATH) @PathVariable("CredentialStatusId") String CredentialStatusId
//     );


//     @Operation(
//         operationId = "registerCredentialStatus",
//         summary = "Register a new Credential Status associated with a public key",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "Credential Status registered successfully.", content=@Content)
//         }
//     )
//     @RequestMapping(
//         method = RequestMethod.POST,
//         value = "/credentialStatuses",
//         consumes = { "application/json" }
//     )
//     abstract ResponseEntity<?> registerCredentialStatus(
//         @Parameter(name = "CredentialStatusDetails", description = "ID registration request", required = true)
//         @Valid @RequestBody CredentialStatusDetails CredentialStatusDetails 
//     );


//     @Operation(
//         operationId = "listCredentialStatuss",
//         summary = "List all registered Credential Statuses",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "A list of registered Credential Statuses", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = ListCredentialStatuses200Response.class))
//             })
//         }
//     )
//     @RequestMapping(
//         method = RequestMethod.GET,
//         value = "/credentialStatuses",
//         produces = { "application/json" }
//     )
//     abstract ResponseEntity<ListCredentialStatuses200Response> listCredentialStatuses(
//         @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
//         @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
//     );

    
//     @Operation(
//         operationId = "updateCredentialStatus",
//         summary = "Update an already registered Credential Status",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "Credential Status updated successfully.", content=@Content),
//             @ApiResponse(responseCode = "404", description = "No Credential Status has the requested ID", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = WrongRequest.class))
//             })
//         }
//     )
//     @RequestMapping(
//         method = RequestMethod.PUT,
//         value = "/credentialStatuses/{credentialStatusId}",
//         consumes = { "application/json" }
//     )
//     abstract ResponseEntity<?> updateCredentialStatus(
//         @Parameter(name = "CredentialStatusId", description = "The ID of the Credential Status to update", required = true, in = ParameterIn.PATH)
//         @PathVariable("CredentialStatusId") String did,
//         @Parameter(name = "UpdateCredentialStatusRequest", description = "Credential Status update request", required = true)
//         @Valid @RequestBody CredentialStatusDetails updateCredentialStatusRequest
//     );


//     @Operation(
//         operationId = "deleteCredentialStatus",
//         summary = "Deletes a specific registered Credential Status",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "The Credential Status was deleted succesfully", content=@Content)
//         }
//     )@RequestMapping(
//         method = RequestMethod.DELETE,
//         value = "/credentialStatuses/{credentialStatusId}",
//         produces = { "application/json" }
//     )    
//     abstract ResponseEntity<?> deleteCredentialStatus(
//         @Parameter(name = "credentialStatusId", description = "The ID of the credential status to delete.", required = true, in = ParameterIn.PATH) @PathVariable("credentialStatusId") String credentialStatusId
//     );


//     @Operation(
//         operationId = "listCredentialStatuss",
//         summary = "List all invalid registered Credential Statuses",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "A list of invalid registered Credential Statuses", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = ListCredentialStatuses200Response.class))
//             })
//         }
//     )
//     @RequestMapping(
//         method = RequestMethod.GET,
//         value = "/credentialStatuses/invalid",
//         produces = { "application/json" }
//     )
//     abstract ResponseEntity<ListCredentialStatuses200Response> listInvalidCredentialStatuses(
//         @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
//         @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
//     );


//     @Operation(
//         operationId = "listCredentialStatuss",
//         summary = "List all revoked registered Credential Statuses",
//         tags = { "Trusted Credential Statuses Registry" },
//         responses = {
//             @ApiResponse(responseCode = "200", description = "A list of revoked registered Credential Statuses", content = {
//                 @Content(mediaType = "application/json", schema = @Schema(implementation = ListCredentialStatuses200Response.class))
//             })
//         }
//     )
//     @RequestMapping(
//         method = RequestMethod.GET,
//         value = "/credentialStatuses/revoked",
//         produces = { "application/json" }
//     )
//     abstract ResponseEntity<ListCredentialStatuses200Response> listRevokedCredentialStatuses(
//         @Parameter(name = "page[after]", description = "Cursor for pagination (starting point)", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[after]", required = false) Integer pageAfter,
//         @Parameter(name = "page[size]", description = "Number of items per page", in = ParameterIn.QUERY)
//         @Valid @RequestParam(value = "page[size]", required = false) Integer pageSize
//     );
// }
