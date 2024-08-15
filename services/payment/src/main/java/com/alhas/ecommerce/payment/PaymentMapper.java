package com.alhas.ecommerce.payment;


import org.springframework.stereotype.Service;

@Service
public record PaymentMapper() {
    public Payment toPayment(PaymentRequest paymentRequest) {

        return  Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amoubn())
                .build();
    }
}
