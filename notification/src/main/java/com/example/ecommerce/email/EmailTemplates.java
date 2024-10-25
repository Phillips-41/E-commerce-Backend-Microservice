package com.example.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order successfully placed");
    @Getter
    private String template;
    @Getter
    private String description;
    EmailTemplates(String template, String description) {
        this.template = template;
        this.description = description;
    }
}
