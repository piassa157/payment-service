package com.piassa.paymentservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piassa.paymentservice.payment.PaymentMessage;
import com.piassa.paymentservice.repositories.PaymentRepository;
import com.piassa.paymentservice.repositories.models.PaymentDocument;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final Clock clock;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository, Clock clock, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.clock = clock;
        this.objectMapper = objectMapper;
    }

    public PaymentDocument process(PaymentMessage message) {
        PaymentDocument document = new PaymentDocument();
        document.setId(message.getUuid().toString());
        document.setTipoPagamento(message.getTipoPagamento());
        Map<String, Object> dadosPagamento = objectMapper.convertValue(
                message.getDadosPagamento(),
                new TypeReference<>() {
                });
        document.setDadosPagamento(dadosPagamento);
        document.setCreatedAt(Instant.now(clock));
        return paymentRepository.save(document);
    }
}
