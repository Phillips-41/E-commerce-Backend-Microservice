package com.example.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Product quantity should be positive")
        double availableQuantity,
        @Positive(message = "Price quantity should be positive")
        BigDecimal price,
        Integer id,
        @NotNull(message = "Product category is required")
        Integer categoryId

) {
}
