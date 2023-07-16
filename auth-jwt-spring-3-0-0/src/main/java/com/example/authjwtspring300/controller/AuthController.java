package com.example.authjwtspring300.controller;

import com.example.authjwtspring300.model.Usuario;
import com.example.authjwtspring300.security.AuthToken;
import com.example.authjwtspring300.security.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/free")
    public String free() {
        return "free";
    }

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthToken> realizarLogin(@RequestBody Usuario user) {
        if (user.getLogin().equals("eu") && user.getSenha().equals("123")) {
            return ResponseEntity.ok(TokenUtil.encodeToken(user));
        }
        return ResponseEntity.status(403).build();
    }
}
