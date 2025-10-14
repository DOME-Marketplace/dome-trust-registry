package com.digitelts.dome.trust.registry.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.model.*;
import java.util.*;
import javax.annotation.Generated;
import javax.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class ParticipantsApiController extends RegistryApiController implements ParticipantsApi{

    private final NativeWebRequest request;

    @Value("${HOST_URL}") // <= If running in Docker
    //@Value("http://localhost") // <= If running in local
    private String host;
    @Value("${PORT}") // <= If running in Docker
    //@Value("8080") // <= If running in local
    private String port;

    public ParticipantsApiController(NativeWebRequest request) {
        super();
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Object> getParticipant(String participantId) {
        ParticipantDetails participant = (ParticipantDetails)findDetails(participantId);
        if(participant==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND, "Participant not found"),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(participant,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertParticipant(@Valid ParticipantDetails insertParticipantRequest) {
        if(this.detailsList.contains(insertParticipantRequest)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Participant already exists"),HttpStatus.BAD_REQUEST);
        this.detailsList.add(insertParticipantRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateParticipant(String participantId,
            @Valid ParticipantDetails updateParticipantRequest) {
        ParticipantDetails participant = (ParticipantDetails)findDetails(participantId);
        if(participant==null) return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Participant not found"),HttpStatus.NOT_FOUND);
        participant.setId(updateParticipantRequest.getId());
        participant.setValidFrom(updateParticipantRequest.getValidFrom());
        participant.setValidTo(updateParticipantRequest.getValidTo());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ListParticipants200Response> listParticipants(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        ListParticipants200Response response = (ListParticipants200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListParticipants200Response(),
            new ListParticipants200ResponseLinks(),
            host+":"+port+"/v4/participants?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "participants/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteParticipant(String participantId) {
        deleteFromId(participantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
