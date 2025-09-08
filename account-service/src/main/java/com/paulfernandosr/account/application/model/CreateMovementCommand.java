package com.paulfernandosr.account.application.model;

import java.math.BigDecimal;

public record CreateMovementCommand(
        String accountNumber,
        BigDecimal amount) {
}
