package com.exercice.cruduser.services.exception;

public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String msg) {
            super(msg);
        }
}
