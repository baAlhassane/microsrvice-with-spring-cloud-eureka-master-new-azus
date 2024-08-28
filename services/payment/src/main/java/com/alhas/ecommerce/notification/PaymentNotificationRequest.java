package com.alhas.ecommerce.notification;

import com.alhas.ecommerce.payment.PaymentMethod;

import javax.swing.text.Position;
import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
