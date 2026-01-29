package com.piassa.paymentservice.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import com.piassa.paymentservice.repositories.PaymentRepository;
import com.piassa.paymentservice.repositories.models.PaymentDocument;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = "payments")
class PaymentListenerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void shouldConsumeMessageAndPersistPayment() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String payload = "{\"uuid\":\"" + uuid + "\",\"tipo_pagamento\":\"PIX\",\"dados_pagamento\":{\"valor\":100.50,\"moeda\":\"BRL\"}}";

        kafkaTemplate.send("payments", payload);

        Optional<PaymentDocument> stored = waitForDocument(uuid, Duration.ofSeconds(10));

        assertThat(stored).isPresent();
        assertThat(stored.get().getId()).isEqualTo(uuid);
        assertThat(stored.get().getTipoPagamento()).isEqualTo("PIX");
        assertThat(stored.get().getDadosPagamento().get("moeda")).isEqualTo("BRL");
    }

    private Optional<PaymentDocument> waitForDocument(String id, Duration timeout) throws InterruptedException {
        Instant deadline = Instant.now().plus(timeout);
        Optional<PaymentDocument> result = paymentRepository.findById(id);
        while (result.isEmpty() && Instant.now().isBefore(deadline)) {
            Thread.sleep(200);
            result = paymentRepository.findById(id);
        }
        return result;
    }
}
