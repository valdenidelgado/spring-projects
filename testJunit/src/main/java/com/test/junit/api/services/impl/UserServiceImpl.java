package com.test.junit.api.services.impl;

import com.test.junit.api.domain.User;
import com.test.junit.api.domain.dtos.UserDTO;
import com.test.junit.api.repositories.UserRepository;
import com.test.junit.api.services.ObjectNotFoundException;
import com.test.junit.api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        return mapper.map(user, UserDTO.class);
    }
}
