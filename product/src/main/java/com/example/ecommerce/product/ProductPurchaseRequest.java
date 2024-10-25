package com.example.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product id shouldn't be null")
        Integer productId,
        @NotNull(message = "quantity shouldn't be null")
        double quantity
) {
}
