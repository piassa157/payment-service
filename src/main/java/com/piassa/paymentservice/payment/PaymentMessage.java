package com.piassa.paymentservice.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public final class PaymentMessage {
    private final UUID uuid;
    private final String tipoPagamento;
    private final JsonNode dadosPagamento;

    @JsonCreator
    public PaymentMessage(
            @JsonProperty("uuid") UUID uuid,
            @JsonProperty("tipo_pagamento") String tipoPagamento,
            @JsonProperty("dados_pagamento") JsonNode dadosPagamento) {
        this.uuid = uuid;
        this.tipoPagamento = tipoPagamento;
        this.dadosPagamento = dadosPagamento;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public JsonNode getDadosPagamento() {
        return dadosPagamento;
    }
}
