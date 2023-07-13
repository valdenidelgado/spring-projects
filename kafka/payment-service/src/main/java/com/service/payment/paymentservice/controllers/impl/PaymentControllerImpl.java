package com.service.payment.paymentservice.controllers.impl;

import com.service.payment.paymentservice.controllers.PaymentController;
import com.service.payment.paymentservice.model.Payment;
import com.service.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payments")
public class PaymentControllerImpl implements PaymentController {

    private final PaymentService paymentService;
    @Override
    public ResponseEntity<Payment> payment(Payment payment) {
        paymentService.sendPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
