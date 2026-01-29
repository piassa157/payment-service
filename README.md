# paymentservice

Servico de pagamentos com Spring Boot, MongoDB e Kafka. Consome mensagens do Kafka e permite envio manual via API REST.

## Requisitos
- Java 21
- Docker e Docker Compose

## Executar com Docker

- Build e subir containers:
  - `make up`
- Ver logs:
  - `make logs`
- Parar tudo:
  - `make down`

O `docker-compose` inicia:
- Kafka (Bitnami)
- MongoDB
- A aplicacao Java

## Configuracao

Variaveis principais (com valores padrao do `compose.yaml`):
- `KAFKA_BOOTSTRAP_SERVERS=kafka:9092`
- `KAFKA_TOPIC=payments`
- `MONGODB_URI=mongodb://root:secret@mongodb:27017/payments?authSource=admin`

## Formato da mensagem do Kafka

```json
{
  "uuid": "c92b0f36-ff20-4d4d-8c4e-6f9f6f39f2f6",
  "tipo_pagamento": "PIX",
  "dados_pagamento": { "valor": 100.50, "moeda": "BRL" }
}
```

## Endpoint manual

- `POST /payments/manual`
- Corpo: mesmo JSON usado no Kafka
- Resposta: documento salvo no MongoDB

## Testes

- Rodar testes locais:
  - `./mvnw test`

Os testes usam Embedded Kafka e MongoDB embutido (nao precisam de servicos externos).
