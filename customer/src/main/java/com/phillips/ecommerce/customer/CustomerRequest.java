package com.phillips.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "firstName is required")
        String firstname,
        @NotNull(message = "lastName is required")
        String lastname,
        @NotNull(message = "email is required")
        @Email
        String email,
        Address address
) {

}
