package com.rocket.course.gestao_vagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocket.course.gestao_vagas.modules.company.dto.AuthCompanyResponse;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class TestUtils {

    public static String objectToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID companyId, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", "COMPANY")
                .withSubject(companyId.toString())
                .sign(algorithm);
    }
}
