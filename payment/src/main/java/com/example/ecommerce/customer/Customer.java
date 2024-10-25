package com.example.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String CustomerId,
        @NotNull(message = "firstname should be required")
        String firstName,
        @NotNull(message = "lastname should be required")
        String lastName,
        @NotNull(message = "email should be required")
        @Email(message = "customer is not correctly formatted")
        String email
) {
}
