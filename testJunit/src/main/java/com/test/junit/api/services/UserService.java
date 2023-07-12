package com.test.junit.api.services;

import com.test.junit.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
