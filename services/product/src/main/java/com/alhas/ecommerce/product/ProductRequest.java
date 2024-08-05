package com.alhas.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = " Product name is required ")
         String name,
         @NotNull(message ="Product description is reqired")
         String description,
         @Positive(message = " Available quantity should be positif ")
         double availableQuantity,
         @Positive(message = " price  should be positif ")
         BigDecimal price,
         @NotNull(message = "Product category is reqired")
         Integer categoryId
)  {
}
