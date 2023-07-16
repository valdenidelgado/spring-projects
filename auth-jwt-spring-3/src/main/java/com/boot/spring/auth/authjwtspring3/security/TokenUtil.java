package com.boot.spring.auth.authjwtspring3.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public static Authentication decodeToken(HttpServletRequest request) {
//        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
//            String token = request.getHeader("Authorization").substring(7);
//            return TokenProvider.getAuthentication(token);
//        }

        if (request.getHeader("Authorization").equals("Bearer token123")) {
            return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        }
        return null;
    }
}
