package com.alhas.ecommerce.kafka;

import com.alhas.ecommerce.customer.CustomerResponse;
import com.alhas.ecommerce.order.PaymentMethod;
import com.alhas.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderreference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
