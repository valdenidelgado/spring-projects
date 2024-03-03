package com.rocket.course.gestao_vagas.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
}
