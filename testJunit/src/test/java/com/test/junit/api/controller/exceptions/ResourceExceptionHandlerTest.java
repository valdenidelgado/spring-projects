package com.test.junit.api.controller.exceptions;

import com.test.junit.api.services.exception.DataIntegratyViolationException;
import com.test.junit.api.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = handler.objectNotFound(
                new ObjectNotFoundException("Object not found"),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object not found", response.getBody().getError());
    }

     @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardError> response = handler.objectNotFound(
                new DataIntegratyViolationException("Email already exists"),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Email already exists", response.getBody().getError());
    }
}