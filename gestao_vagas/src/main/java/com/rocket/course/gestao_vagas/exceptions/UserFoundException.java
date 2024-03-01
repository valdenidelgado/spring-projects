package com.rocket.course.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("User already exists in the system.");
    }
}
