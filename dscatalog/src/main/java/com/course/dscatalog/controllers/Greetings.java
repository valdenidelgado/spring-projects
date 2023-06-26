package com.course.dscatalog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/greetings")
public class Greetings {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/bye")
    public String bye() {
        return "Bye World!";
    }
}
