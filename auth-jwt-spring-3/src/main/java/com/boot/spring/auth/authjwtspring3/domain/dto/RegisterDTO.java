package com.boot.spring.auth.authjwtspring3.domain.dto;

import com.boot.spring.auth.authjwtspring3.domain.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {}
