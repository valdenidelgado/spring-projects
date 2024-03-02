package com.rocket.course.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rocket.course.gestao_vagas.exceptions.UserFoundException;
import com.rocket.course.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.rocket.course.gestao_vagas.modules.company.dto.AuthCompanyResponse;
import com.rocket.course.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    private final CompanyRepository repository;
    private final PasswordEncoder encoder;

    public AuthCompanyUseCase(CompanyRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public AuthCompanyResponse execute(AuthCompanyDTO dto) throws AuthenticationException {
        var company = this.repository.findByUsername(dto.getUsername())
                .orElseThrow(UserFoundException::new);
        var passwordMatches = this.encoder.matches(dto.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", "COMPANY")
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return AuthCompanyResponse.builder()
                .access_token(token)
                .expires_in(Date.from(expiresIn))
                .build();
    }
}
