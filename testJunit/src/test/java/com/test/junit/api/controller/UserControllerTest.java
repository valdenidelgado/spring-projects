package com.test.junit.api.controller;

import com.test.junit.api.domain.User;
import com.test.junit.api.domain.dtos.UserDTO;
import com.test.junit.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "User";
    public static final String EMAIL = "john@doe.com";
    public static final String PASSWORD = "123456";

    @InjectMocks
    private UserController controller;
    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        ResponseEntity<User> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(User.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(userDTO));
        ResponseEntity<List<UserDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.create(userDTO);

        assertNotNull(response);
        assertNull(response.getBody());
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());
        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        assertNull(response.getBody());
        assertEquals(204, response.getStatusCodeValue());
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}