package com.example.payrollapi.controllers;

import com.example.payrollapi.domain.Payroll;
import com.example.payrollapi.domain.User;
import com.example.payrollapi.feignClients.UserFeign;
import com.example.payrollapi.services.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PayrollController {

    private final PayrollService service;

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payroll) {
        return ResponseEntity.ok().body(service.getPayment(workerId, payroll));
    }
}
