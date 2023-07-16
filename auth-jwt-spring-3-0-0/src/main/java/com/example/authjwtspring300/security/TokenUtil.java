package com.example.authjwtspring300.security;

import com.example.authjwtspring300.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String EMISSOR = "test.com";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY = "01234567890123456789012345678901";
    private static final long UM_SEGUNDO = 1000;
    private static final long UM_MINUTO = 60 * UM_SEGUNDO;

    public static AuthToken encodeToken(Usuario user) {
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String token = Jwts.builder()
                .setIssuer(EMISSOR)
                .setSubject(user.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return new AuthToken(TOKEN_HEADER + token);
    }

    public static Authentication decodeToken(HttpServletRequest request) {

        try {
            String token = request.getHeader("Authorization").replace(TOKEN_HEADER, "");

            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            String usuario = jwsClaims.getBody().getSubject();
            String emissor = jwsClaims.getBody().getIssuer();
            Date expiration = jwsClaims.getBody().getExpiration();

            if (usuario.length() > 0 && emissor.equals(EMISSOR) && expiration.after(
                    new Date(System.currentTimeMillis()))) {
                return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
            }
        } catch (Exception e) {
            System.out.println("Token inválido");
        }

        return null;
    }
}
