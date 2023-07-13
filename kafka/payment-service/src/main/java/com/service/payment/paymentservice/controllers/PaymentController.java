package com.service.payment.paymentservice.controllers;

import com.service.payment.paymentservice.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentController {

    @PostMapping
    ResponseEntity<Payment> payment(@RequestBody Payment payment);
}
