package com.example.payrollapi.feignClients;

import com.example.payrollapi.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-api") // se tiver no eureka, não precisa do url e faz o balanceamento de carga
public interface UserFeign {

    @GetMapping(value = "/users/{id}")
    ResponseEntity<User> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<User>> findAll();
}
