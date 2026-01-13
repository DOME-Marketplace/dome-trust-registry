package com.digitelts.dome.trust.registry.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.digitelts.dome.trust.registry.exceptions.AuthException;
import com.digitelts.dome.trust.registry.model.*;
import com.digitelts.dome.trust.registry.repositories.ParticipantRepository;
import com.digitelts.dome.trust.registry.services.AuthService;

import java.math.BigInteger;
import java.util.*;
import javax.annotation.Generated;
import javax.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.eBSILikeTrustedRegistry.base-path:/v4}")
public class ParticipantsApiController extends RegistryApiController<ParticipantDetails> implements ParticipantsApi{

    private final NativeWebRequest request;
    private final Web3Client web3;

    public ParticipantsApiController(NativeWebRequest request, Web3Client w3Client, ParticipantRepository repo) {
        super(repo);
        this.request = request;
        this.web3 = w3Client;
    }

    public ParticipantsApiController(NativeWebRequest request, Web3Client w3Client, ParticipantRepository repo, AuthService auth) {
        super(repo, auth);
        this.request = request;
        this.web3 = w3Client;
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
    public ResponseEntity<?> insertParticipant(@Valid ParticipantDetails insertParticipantRequest, String bearerToken) {
        try{
            if(!this.insertRegistry(insertParticipantRequest, bearerToken)) return new ResponseEntity<>(new WrongRequest(HttpStatus.BAD_REQUEST.value(), "Participant already exists"),HttpStatus.BAD_REQUEST);
            byte[] hash = web3.includeDID(insertParticipantRequest.getId());
            String hexHash = String.format("%064x", new BigInteger(1, hash));
            return new ResponseEntity<>(hexHash,HttpStatus.OK);
        } catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateParticipant(String participantId,
            @Valid ParticipantDetails updateParticipantRequest, String bearerToken) {
        try{
            if(this.updateRegistry(participantId,updateParticipantRequest,bearerToken)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(new WrongRequest(HttpStatus.NOT_FOUND.value(), "Participant not found"), HttpStatus.NOT_FOUND);
        }catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<ListParticipants200Response> listParticipants(@Valid Integer pageAfter,
            @Valid Integer pageSize) {
        ListParticipants200Response response = (ListParticipants200Response) this.listDetails(
            pageAfter,
            pageSize,
            new ListParticipants200Response(),
            new ListParticipants200ResponseLinks(),
            this.API_URL+"participants?page%%5Bafter%%5D=%d&page%%5Bsize%%5D=%d",
            "participants/"                   
        );

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteParticipant(String participantId, String bearerToken) {
        try{
            deleteFromId(participantId, bearerToken);
            web3.removeDID(participantId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(AuthException e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(new WrongRequest(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
