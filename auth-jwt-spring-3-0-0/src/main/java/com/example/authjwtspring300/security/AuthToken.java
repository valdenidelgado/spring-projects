package com.example.authjwtspring300.security;

import java.util.Objects;

public class AuthToken {
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(getToken(), authToken.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }
}
