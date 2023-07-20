package com.project.demoproject.unittests.mockito.services;

import com.project.demoproject.exceptions.RequiredObjectIsNullException;
import com.project.demoproject.model.Book;
import com.project.demoproject.model.dto.v1.BookDTO;
import com.project.demoproject.repositories.BookRepository;
import com.project.demoproject.services.BookService;
import com.project.demoproject.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLaunchDate());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
    }

    @Test
    void create() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        BookDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.save(any())).thenReturn(entity);

        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLaunchDate());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());

    }

    @Test
    void findAll() {
        var list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var result = service.findAll();
        assertNotNull(result);
        assertNotNull(result.get(0).getKey());
        assertNotNull(result.get(0).getLinks());
        assertEquals(14, result.size());
        assertNotNull(result.get(0).getLaunchDate());
        assertTrue(result.get(0).toString().contains("links: [</api/book/v1/0>;rel=\"self\"]"));
        assertEquals("Some Author0", result.get(0).getAuthor());
        assertEquals("Some Title0", result.get(0).getTitle());
        assertEquals(25D, result.get(0).getPrice());


        assertNotNull(result.get(1).getLaunchDate());
        assertTrue(result.get(1).toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", result.get(1).getAuthor());
        assertEquals("Some Title1", result.get(1).getTitle());
        assertEquals(25D, result.get(1).getPrice());
    }



    @Test
    void update() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        BookDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        var result = service.update(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLaunchDate());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
    }

    @Test
    void delete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        doNothing().when(repository).delete(any());

        service.delete(1L);
    }

    @Test
    void createWithNullPerson() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
        String expected = "It is not allowed to persist a null object!";
        String actual = ex.getMessage();
        assertTrue(actual.contains(expected));
        assertEquals(expected, actual);
    }

    @Test
    void updateWithNullPerson() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
        String expected = "It is not allowed to persist a null object!";
        String actual = ex.getMessage();
        assertTrue(actual.contains(expected));
        assertEquals(expected, actual);
    }
}