package com.digitelts.dome.trust.registry.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.repositories.ParticipantRepository;
import com.digitelts.dome.trust.registry.model.ParticipantDetails;
import com.digitelts.dome.trust.registry.model.Web3Client;
import com.digitelts.dome.trust.registry.model.List200Response;
import com.digitelts.dome.trust.registry.model.List200ResponseLinks;

@ExtendWith(MockitoExtension.class)
public class ParticipantsRegistryApiControllerTest {
    
    @Mock
    private NativeWebRequest request;
    @Mock
    private ParticipantRepository repository;
    @Mock
    private Web3Client web3;
    private ParticipantsApiController apiController;
    private String mockedId = "did:key:foo";

    @BeforeEach
    void setUp(){
        apiController = new ParticipantsApiController(request, web3, repository);
    }

    ParticipantDetails getMockedDetails(){
        return new ParticipantDetails(mockedId);
    }

    List<ParticipantDetails> getMockedDetails(int size){
        List<ParticipantDetails> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String id = mockedId + ":" + i;
            list.add(
                new ParticipantDetails(id)
            );
        }
        return list;
    }

    /*** GET ****/
    @Test
    void testGetParticipant_whenParticipantExists_shouldReturnParticipant(){
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.findById(mockedId))
            .thenReturn(Optional.of(mockedDetails));

        ResponseEntity<?> result = apiController.getParticipant(mockedId);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(ParticipantDetails.class, result.getBody());
        
        ParticipantDetails retrievedParticipant = (ParticipantDetails) result.getBody();
        assertEquals(mockedId, retrievedParticipant.getId());
        verify(repository).findById(mockedId);
    }

    @Test
    void testGetParticipant_whenParticipantDoesntExist_shouldReturnHttp404(){
        when(repository.findById(mockedId))
        .thenReturn(Optional.empty());

        ResponseEntity<?> result = apiController.getParticipant(mockedId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository).findById(mockedId);
    }

    /*** POST ***/
    @Test
    void testInsertParticipant_whenParticipantDoesntExist_shouldReturnHttp200(){
        try{
            ParticipantDetails mockedDetails = getMockedDetails();

            when(repository.existsById(mockedId))
            .thenReturn(false);

            when(web3.includeDID(mockedId))
            .thenReturn(Web3Client.sha256String(mockedId));

            ResponseEntity<?> result = apiController.insertParticipant(mockedDetails);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            verify(repository).existsById(mockedId);
            verify(repository).saveAndFlush(mockedDetails);
            verify(web3).includeDID(mockedId);
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    void testInsertParticipant_whenParticipantExists_shouldReturnHttp400(){
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.insertParticipant(mockedDetails);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** PUT ***/
    @Test
    void testUpdateParticipant_whenParticipantExists_shouldReturnHttp200(){
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.updateParticipant(mockedId, mockedDetails);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testUpdateParticipant_whenParticipantDoesntExist_shouldReturnHttp404(){
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.updateParticipant(mockedId, mockedDetails);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** LIST ***/
    @Test
    void testListParticipants_whithNotEmptyList_shouldReturnPaginatedResponse(){
        List<ParticipantDetails> mockedList = getMockedDetails(10);
        int pageSize = 5;

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listParticipants(0,pageSize);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(List200Response.class, result.getBody());
        List200Response list200 = (List200Response) result.getBody();
        assertEquals(pageSize, list200.getPageSize());
        assertEquals(pageSize, list200.getItems().size());
        assertNotNull(list200.getLinks());
        assertInstanceOf(List200ResponseLinks.class, list200.getLinks());
        List200ResponseLinks links = list200.getLinks();
        assertNotNull(links.getFirst());
        assertNotNull(links.getLast());
        assertNotNull(links.getNext());
    }

    @Test
    void testListParticipants_whithEmptyList_shouldReturnEmptyPaginatedResponse(){
        List<ParticipantDetails> mockedList = getMockedDetails(0);
        int pageSize = 5;

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listParticipants(0,pageSize);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(List200Response.class, result.getBody());
        List200Response list200 = (List200Response) result.getBody();
        assertEquals(pageSize, list200.getPageSize());
        assertEquals(0, list200.getItems().size());
        assertNotNull(list200.getLinks());
        assertInstanceOf(List200ResponseLinks.class, list200.getLinks());
        List200ResponseLinks links = list200.getLinks();
        assertNotNull(links.getFirst());
        assertNotNull(links.getLast());
        assertNull(links.getNext());
    }

    /*** DELETE ***/
    @Test
    void testDeleteParticipant_whenParticipantExists_shouldReturnHttp200(){
        try{
            doNothing().when(web3).removeDID(mockedId);

            ResponseEntity<?> result = apiController.deleteParticipant(mockedId);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            verify(repository).deleteById(mockedId);
            verify(web3).removeDID(mockedId);
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    void testDeleteParticipant_whenParticipantDoesntExists_shouldReturnHttp200(){
        try{
            doNothing().when(web3).removeDID(mockedId);

            ResponseEntity<?> result = apiController.deleteParticipant(mockedId);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            verify(repository).deleteById(mockedId);
            verify(web3).removeDID(mockedId);
        }catch(Exception e){
            fail(e);
        }
    }
}
