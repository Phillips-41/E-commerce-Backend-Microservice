package com.phillips.ecommerce.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> msg
) {
}
