package com.alhas.ecommerce.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product is mandatory ")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
