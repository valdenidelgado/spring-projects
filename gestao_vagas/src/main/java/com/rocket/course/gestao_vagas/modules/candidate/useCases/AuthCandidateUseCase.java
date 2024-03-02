package com.rocket.course.gestao_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rocket.course.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.rocket.course.gestao_vagas.modules.candidate.dto.AuthCandidateResponse;
import com.rocket.course.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    private final CandidateRepository repository;
    private final PasswordEncoder encoder;

    public AuthCandidateUseCase(CandidateRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public AuthCandidateResponse execute(AuthCandidateDTO dto) throws AuthenticationException {
        var candidate = this.repository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect."));
        var passwordMatches = this.encoder.matches(dto.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", List.of("CANDIDATE"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        return AuthCandidateResponse.builder()
                .access_token(token)
                .expires_in(Date.from(expiresIn))
                .build();
    }
}
