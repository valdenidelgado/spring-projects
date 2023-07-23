package com.project.demoproject.unittests.mockito.services;

import com.project.demoproject.exceptions.RequiredObjectIsNullException;
import com.project.demoproject.model.Person;
import com.project.demoproject.model.dto.v1.PersonDTO;
import com.project.demoproject.repositories.PersonRepository;
import com.project.demoproject.services.PersonService;
import com.project.demoproject.unittests.mapper.mocks.MockPerson;
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
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.save(any())).thenReturn(entity);

        var result = service.create(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

//    @Test
//    void findAll() {
//        var list = input.mockEntityList();
//
//        when(repository.findAll()).thenReturn(list);
//
//        var result = service.findAll(pageable);
//        assertNotNull(result);
//        assertNotNull(result.get(0).getKey());
//        assertNotNull(result.get(0).getLinks());
//        assertEquals(14, result.size());
//        assertTrue(result.get(0).toString().contains("links: [</api/person/v1/0>;rel=\"self\"]"));
//        assertEquals("Addres Test0", result.get(0).getAddress());
//        assertEquals("First Name Test0", result.get(0).getFirstName());
//        assertEquals("Last Name Test0", result.get(0).getLastName());
//        assertEquals("Male", result.get(0).getGender());
//
//
//        assertTrue(result.get(1).toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//        assertEquals("Addres Test1", result.get(1).getAddress());
//        assertEquals("First Name Test1", result.get(1).getFirstName());
//        assertEquals("Last Name Test1", result.get(1).getLastName());
//        assertEquals("Female", result.get(1).getGender());
//    }



    @Test
    void update() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setKey(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        var result = service.update(dto);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
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