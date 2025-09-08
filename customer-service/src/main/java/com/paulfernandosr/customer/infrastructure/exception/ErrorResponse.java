package com.paulfernandosr.customer.infrastructure.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp) {
}
