package com.boot.spring.auth.authjwtspring3.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            logger.info("Token: {}", token);
//        }

        if(authHeader != null) {
            Authentication auth = TokenUtil.decodeToken(request);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                logger.info("ERRO NO TOKEN");
                ErrorDTO erro = new ErrorDTO(401, "Token inválido");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }
        }


        logger.info("Request: {}", request);
        logger.info("Auth header: {}", authHeader);
        filterChain.doFilter(request, response);
    }
}
