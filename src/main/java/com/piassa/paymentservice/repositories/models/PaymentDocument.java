package com.piassa.paymentservice.repositories.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document("payments")
public class PaymentDocument {
    @Id
    private String id;
    private String tipoPagamento;
    private Map<String, Object> dadosPagamento;
    private Instant createdAt;

    public PaymentDocument() {
    }

    public PaymentDocument(String id, String tipoPagamento, Map<String, Object> dadosPagamento, Instant createdAt) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.dadosPagamento = dadosPagamento;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Map<String, Object> getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(Map<String, Object> dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
