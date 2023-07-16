package com.boot.spring.auth.authjwtspring3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/auth")
    public String auth() {
        return "Auth";
    }

    @GetMapping("/free")
    public String free() {
        return "Free";
    }
}