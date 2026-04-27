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
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.test.util.*;

import com.digitelts.dome.trust.registry.repositories.AccessNodeRepository;
import com.digitelts.dome.trust.registry.services.AuthService;
import com.digitelts.dome.trust.registry.model.AccessNodeDetails;
import com.digitelts.dome.trust.registry.model.List200Response;
import com.digitelts.dome.trust.registry.model.List200ResponseLinks;

@ExtendWith(MockitoExtension.class)
public class AccessNodeRegistryApiControllerTest {
    
    @Mock
    private NativeWebRequest request;
    @Mock
    private AccessNodeRepository repository;
    @Mock(lenient = true)
    private AuthService auth;
    @InjectMocks
    private AccessNodeRegistryApiController apiController;
    private String mockedId = "dlt_address";
    private String mockedName = "access_node_name";
    private String mockedToken = "some_user_jwt";

    @BeforeEach
    void setUp() throws Exception{
        doNothing().when(auth).init();
        doNothing().when(auth).validateToken(mockedToken);
        // Set API_URL var
        ReflectionTestUtils.setField(apiController, "API_URL", "http://localhost:8080/v0/");
    }

    AccessNodeDetails getMockedDetails(){
        return new AccessNodeDetails(mockedId, mockedName);
    }

    List<AccessNodeDetails> getMockedDetails(int size){
        List<AccessNodeDetails> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String id = mockedId + ":" + i;
            String name = mockedName + ":" + i;
            list.add(
                new AccessNodeDetails(id, name)
            );
        }
        return list;
    }

    /*** GET ****/
    @Test
    void testGetAccessNode_whenAccessNodeExists_shouldReturnAccessNode(){
        AccessNodeDetails accessNode = getMockedDetails();

        when(repository.findById(mockedId))
            .thenReturn(Optional.of(
                new AccessNodeDetails(mockedId, mockedName)
            ));

        ResponseEntity<?> result = apiController.getAccessNode(mockedId);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(AccessNodeDetails.class, result.getBody());
        
        AccessNodeDetails retrievedAccessNode = (AccessNodeDetails) result.getBody();
        assertEquals(accessNode.getName(), retrievedAccessNode.getName());
        assertEquals(accessNode.getId(), retrievedAccessNode.getId());
        verify(repository)
            .findById(mockedId);
    }

    @Test
    void testGetAccessNode_whenAccessNodeDoesntExist_shouldReturnHttp404(){
        when(repository.findById(mockedId))
        .thenReturn(Optional.empty());

        ResponseEntity<?> result = apiController.getAccessNode(mockedId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository)
            .findById(mockedId);
    }

    /*** POST ***/
    @Test
    void testRegisterAccessNode_whenAccessNodeDoesntExist_shouldReturnHttp200(){
        AccessNodeDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.registerAccessNode(mockedDetails, mockedToken);

        // Check token is validated
        assertDoesNotThrow(() -> {
            verify(auth).validateToken(mockedToken);
        });

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testRegisterAccessNode_whenAccessNodeExists_shouldReturnHttp400(){
        AccessNodeDetails mockedDetails = new AccessNodeDetails(
            mockedId,
            mockedName
        );

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.registerAccessNode(mockedDetails, mockedToken);

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
    void testUpdateAccessNode_whenAccessNodeExists_shouldReturnHttp200(){
        AccessNodeDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.updateAccessNode(mockedId, mockedDetails, mockedToken);

        // Check token is validated
        assertDoesNotThrow(() -> {
            verify(auth).validateToken(mockedToken);
        });

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testUpdateAccessNode_whenAccessNodeDoesntExist_shouldReturnHttp404(){
        AccessNodeDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.updateAccessNode(mockedId, mockedDetails, mockedToken);

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
    void testListAccessNodes_whithNotEmptyList_shouldReturnPaginatedResponse(){
        // Generating a list with 15 mocked values
        List<AccessNodeDetails> mockedList = getMockedDetails(15);
        // 15 items, 5 per page -> 3 pages in total
        int pageSize = 5;
        String expectedUrl = "http://localhost:8080/v0/accessNodes?page[after]=%d&page[size]=%d";

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listAccessNodes(1,pageSize);

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
    void testListAccessNodes_whithEmptyList_shouldReturnEmptyPaginatedResponse(){
        // Generating an empty list
        List<AccessNodeDetails> mockedList = getMockedDetails(0);
        int pageSize = 5;
        String expectedUrl = "http://localhost:8080/v0/accessNodes?page[after]=%d&page[size]=%d";

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listAccessNodes(0,pageSize);

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
}
