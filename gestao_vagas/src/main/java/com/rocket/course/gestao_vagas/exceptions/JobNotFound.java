package com.rocket.course.gestao_vagas.exceptions;

public class JobNotFound extends RuntimeException {
    public JobNotFound() {
        super("Job not found");
    }
}
