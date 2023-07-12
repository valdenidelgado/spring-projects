package com.test.junit.api.services.impl;

import com.test.junit.api.domain.User;
import com.test.junit.api.repositories.UserRepository;
import com.test.junit.api.services.ObjectNotFoundException;
import com.test.junit.api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}
