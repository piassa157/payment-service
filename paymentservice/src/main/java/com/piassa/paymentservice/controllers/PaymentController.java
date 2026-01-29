package com.piassa.paymentservice.controllers;

import com.piassa.paymentservice.payment.PaymentMessage;
import com.piassa.paymentservice.repositories.models.PaymentDocument;
import com.piassa.paymentservice.services.PaymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/manual")
    public ResponseEntity<PaymentDocument> manualPayment(@RequestBody PaymentMessage message) {
        PaymentDocument saved = paymentService.process(message);
        return ResponseEntity.ok(saved);
    }
}
