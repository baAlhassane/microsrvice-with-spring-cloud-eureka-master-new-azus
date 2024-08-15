package com.alhas.ecommerce.payment;

import com.alhas.ecommerce.customer.CustomerResponse;
import com.alhas.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {

}
