package com.example.ecommerce.kafka.order;

public record CustomerResponse(

        String id,
        String firstName,
        String lastName,
        String email
) {
}
