package com.test.junit.api.services;

import com.test.junit.api.domain.User;
import com.test.junit.api.domain.dtos.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<UserDTO> findAll();
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(Integer id);
}
