package com.alhas.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer producId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
