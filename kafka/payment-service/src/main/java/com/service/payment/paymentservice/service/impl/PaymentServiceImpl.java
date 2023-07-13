package com.service.payment.paymentservice.service.impl;

import com.service.payment.paymentservice.model.Payment;
import com.service.payment.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Log4j2
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    @SneakyThrows
    @Override
    public void sendPayment(Payment payment) {
        log.info("Payment sent: {}", payment);
        Thread.sleep(1000);

        log.info("Payment processed: {}", payment);
        kafkaTemplate.send("payment-topic", payment);
    }
}
