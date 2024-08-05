package com.alhas.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = " Product is mandatory ")
        Integer productId,
        @Positive(message = " quantity is madatory ")
        double quantity
)  { }
