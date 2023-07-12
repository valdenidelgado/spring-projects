package com.test.junit.api.services;

import com.test.junit.api.domain.User;
import com.test.junit.api.domain.dtos.UserDTO;

public interface UserService {

    UserDTO findById(Integer id);
}
