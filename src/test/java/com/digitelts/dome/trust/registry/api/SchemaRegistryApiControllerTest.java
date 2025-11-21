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

import com.digitelts.dome.trust.registry.repositories.SchemaRepository;
import com.digitelts.dome.trust.registry.model.SchemaDetails;
import com.digitelts.dome.trust.registry.model.List200Response;
import com.digitelts.dome.trust.registry.model.List200ResponseLinks;

@ExtendWith(MockitoExtension.class)
public class SchemaRegistryApiControllerTest {
    
    @Mock
    private NativeWebRequest request;
    @Mock
    private SchemaRepository repository;
    private SchemaRegistryApiController apiController;
    private String mockedId = "trusted_schema_id";
    private String mockedData = "trusted_schema_data";

    @BeforeEach
    void setUp(){
        apiController = new SchemaRegistryApiController(request, repository);
    }

    SchemaDetails getMockedDetails(){
        return new SchemaDetails(mockedId, mockedData);
    }

    List<SchemaDetails> getMockedDetails(int size){
        List<SchemaDetails> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            String id = mockedId + ":" + i;
            String data = mockedData + ":" + i;
            list.add(
                new SchemaDetails(id, data)
            );
        }
        return list;
    }

    /*** GET ****/
    @Test
    void testGetSchema_whenSchemaExists_shouldReturnSchema(){
        SchemaDetails mockedDetails = getMockedDetails();

        when(repository.findById(mockedId))
            .thenReturn(Optional.of(mockedDetails));

        ResponseEntity<?> result = apiController.getSchema(mockedId);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertInstanceOf(SchemaDetails.class, result.getBody());
        
        SchemaDetails retrievedSchema = (SchemaDetails) result.getBody();
        assertEquals(mockedId, retrievedSchema.getId());
        assertEquals(mockedData, retrievedSchema.getSchemaData());
        verify(repository)
            .findById(mockedId);
    }

    @Test
    void testGetSchema_whenSchemaDoesntExist_shouldReturnHttp404(){
        when(repository.findById(mockedId))
        .thenReturn(Optional.empty());

        ResponseEntity<?> result = apiController.getSchema(mockedId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository)
            .findById(mockedId);
    }

    /*** POST ***/
    @Test
    void testRegisterSchema_whenSchemaDoesntExist_shouldReturnHttp200(){
        SchemaDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.registerSchema(mockedDetails);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testRegisterSchema_whenSchemaExists_shouldReturnHttp400(){
        SchemaDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.registerSchema(mockedDetails);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** PUT ***/
    @Test
    void testUpdateSchema_whenSchemaExists_shouldReturnHttp200(){
        SchemaDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(true);

        ResponseEntity<?> result = apiController.updateSchema(mockedId, mockedDetails);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository).saveAndFlush(mockedDetails);
    }

    @Test
    void testUpdateSchema_whenSchemaDoesntExist_shouldReturnHttp404(){
        SchemaDetails mockedDetails = getMockedDetails();

        when(repository.existsById(mockedId))
        .thenReturn(false);

        ResponseEntity<?> result = apiController.updateSchema(mockedId, mockedDetails);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(repository).existsById(mockedId);
        verify(repository, never()).saveAndFlush(mockedDetails);
    }

    /*** LIST ***/
    @Test
    void testListSchemas_whithNotEmptyList_shouldReturnPaginatedResponse(){
        List<SchemaDetails> mockedList = getMockedDetails(10);
        int pageSize = 5;

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listSchemas(0,pageSize);

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
    void testListSchemas_whithEmptyList_shouldReturnEmptyPaginatedResponse(){
        List<SchemaDetails> mockedList = getMockedDetails(0);
        int pageSize = 5;

        when(repository.findAll())
        .thenReturn(mockedList);

        ResponseEntity<?> result = apiController.listSchemas(0,pageSize);

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
    void testDeleteSchema_whenSchemaExists_shouldReturnHttp200(){
        ResponseEntity<?> result = apiController.deleteSchema(mockedId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).deleteById(mockedId);
    }

    @Test
    void testDeleteSchema_whenSchemaDoesntExists_shouldReturnHttp200(){
        ResponseEntity<?> result = apiController.deleteSchema(mockedId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(repository).deleteById(mockedId);
    }
}
