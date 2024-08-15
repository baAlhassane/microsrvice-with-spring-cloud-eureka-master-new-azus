package com.alhas.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname is resuired")
        String firstname,
        @NotNull(message = "Laststname is resuired")
        String lastname,
        @NotNull(message = "Email is required ")
        @Email(message = "customer iemail is not correctely formatted ")
        String email

) {
}
