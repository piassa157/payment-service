package com.piassa.paymentservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.piassa.paymentservice.repositories.models.PaymentDocument;

public interface PaymentRepository extends MongoRepository<PaymentDocument, String> {
}
