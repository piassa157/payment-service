package com.piassa.paymentservice.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piassa.paymentservice.services.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {
    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    public PaymentListener(ObjectMapper objectMapper, PaymentService paymentService) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(String payload) {
        try {
            PaymentMessage message = objectMapper.readValue(payload, PaymentMessage.class);
            paymentService.process(message);
        } catch (Exception ex) {
            logger.error("Failed to process payment payload: {}", payload, ex);
        }
    }
}
