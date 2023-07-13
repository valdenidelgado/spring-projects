package com.service.payment.paymentservice.service;

import com.service.payment.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
