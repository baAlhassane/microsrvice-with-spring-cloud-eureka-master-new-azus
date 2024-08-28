package com.alhas.ecommerce.kafka;

import com.alhas.ecommerce.customer.CustomerResponse;
import com.alhas.ecommerce.order.PaymentMethod;
import com.alhas.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
