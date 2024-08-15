package com.alhas.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderreference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFistName,
        String customerLastName,
        String customerEmail
        ) {

}
