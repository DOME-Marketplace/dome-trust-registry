package com.digitelts.dome.trust.registry.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.NativeWebRequest;
import com.digitelts.dome.trust.registry.repositories.ParticipantRepository;
import com.digitelts.dome.trust.registry.services.AuthService;
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
    @Mock(lenient = true)
    private AuthService auth;
    @InjectMocks
    private ParticipantsApiController apiController;
    private String mockedId = "did:key:foo";
    private String mockedToken = "some_user_jwt";

    @BeforeEach
    void setUp() throws Exception{
        doNothing().when(auth).init();
        doNothing().when(auth).validateToken(mockedToken);
        // Set API_URL var
        ReflectionTestUtils.setField(apiController, "API_URL", "http://localhost:8080/v0/");
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
            .thenReturn(Optional.of(
                new ParticipantDetails(mockedId)
            ));

        ResponseEntity<?> result = apiController.getParticipant(mockedId);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(ParticipantDetails.class, result.getBody());
        
        ParticipantDetails retrievedParticipant = (ParticipantDetails) result.getBody();
        assertEquals(mockedDetails.getId(), retrievedParticipant.getId());
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

            ResponseEntity<?> result = apiController.insertParticipant(mockedDetails, mockedToken);

            // Check token is validated
            assertDoesNotThrow(() -> {
                verify(auth).validateToken(mockedToken);
            });

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

        ResponseEntity<?> result = apiController.insertParticipant(mockedDetails, mockedToken);

        // Check token is validated
        assertDoesNotThrow(() -> {
            verify(auth).validateToken(mockedToken);
        });

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** PUT ***/
    @Test
    void testUpdateParticipant_whenParticipantExists_shouldReturnHttp200() {
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.updateParticipant(mockedId, mockedDetails, mockedToken);

        // Check token is validated
        assertDoesNotThrow(() -> {
            verify(auth).validateToken(mockedToken);
        });

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testUpdateParticipant_whenParticipantDoesntExist_shouldReturnHttp404() {
        ParticipantDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.updateParticipant(mockedId, mockedDetails, mockedToken);

        // Check token is validated
        assertDoesNotThrow(() -> {
            verify(auth).validateToken(mockedToken);
        });

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** LIST ***/
    @Test
    void testListParticipants_whithNotEmptyList_shouldReturnPaginatedResponse(){
        // Generating a list with 15 mocked values
        List<ParticipantDetails> mockedList = getMockedDetails(15);
        // 15 items, 5 per page -> 3 pages in total
        int pageSize = 5;
        String expectedUrl = "http://localhost:8080/v0/participants?page[after]=%d&page[size]=%d";

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listParticipants(1,pageSize);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(List200Response.class, result.getBody());
        List200Response list200 = (List200Response) result.getBody();
        assertEquals(pageSize, list200.getPageSize());
        assertEquals(pageSize, list200.getItems().size());
        assertNotNull(list200.getLinks());
        assertInstanceOf(List200ResponseLinks.class, list200.getLinks());
        List200ResponseLinks links = list200.getLinks();
        assertEquals(String.format(expectedUrl, 0, 5), URLDecoder.decode(links.getFirst(), StandardCharsets.UTF_8));
        assertEquals(String.format(expectedUrl, 2, 5), URLDecoder.decode(links.getLast(), StandardCharsets.UTF_8));
        assertEquals(String.format(expectedUrl, 2, 5), URLDecoder.decode(links.getNext(), StandardCharsets.UTF_8));
        assertEquals(String.format(expectedUrl, 0, 5), URLDecoder.decode(links.getPrev(), StandardCharsets.UTF_8));
    }

    @Test
    void testListParticipants_whithEmptyList_shouldReturnEmptyPaginatedResponse(){
        // Generating an empty list
        List<ParticipantDetails> mockedList = getMockedDetails(0);
        int pageSize = 5;
        String expectedUrl = "http://localhost:8080/v0/participants?page[after]=%d&page[size]=%d";

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
        // Empty list -> Just one page
        assertEquals(String.format(expectedUrl, 0, 5), URLDecoder.decode(links.getFirst(), StandardCharsets.UTF_8));
        assertEquals(String.format(expectedUrl, 0, 5), URLDecoder.decode(links.getLast(), StandardCharsets.UTF_8));
        assertNull(links.getNext());
        assertNull(links.getPrev());
    }

    /*** DELETE ***/
    @Test
    void testDeleteParticipant_whenParticipantExists_shouldReturnHttp200() {
        try{
            doNothing().when(web3).removeDID(mockedId);

            ResponseEntity<?> result = apiController.deleteParticipant(mockedId, mockedToken);

            // Check token is validated
            assertDoesNotThrow(() -> {
                verify(auth).validateToken(mockedToken);
            });

            assertEquals(HttpStatus.OK, result.getStatusCode());
            verify(repository).deleteById(mockedId);
            verify(web3).removeDID(mockedId);
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    void testDeleteParticipant_whenParticipantDoesntExists_shouldReturnHttp200() {
        try{
            doNothing().when(web3).removeDID(mockedId);

            ResponseEntity<?> result = apiController.deleteParticipant(mockedId, mockedToken);

            // Check token is validated
            assertDoesNotThrow(() -> {
                verify(auth).validateToken(mockedToken);
            });

            assertEquals(HttpStatus.OK, result.getStatusCode());
            verify(repository).deleteById(mockedId);
            verify(web3).removeDID(mockedId);
        }catch(Exception e){
            fail(e);
        }
    }
}
