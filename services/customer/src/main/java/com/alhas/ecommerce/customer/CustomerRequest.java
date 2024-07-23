package com.alhas.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message="customer firstname is required")
        String firstname,
        @NotNull(message="customer lastnamename is required")
        String lastname,
        @NotNull(message="customer email is required")
        @Email(message = "customer email must not null")
        String email,
        Address address
) { }
