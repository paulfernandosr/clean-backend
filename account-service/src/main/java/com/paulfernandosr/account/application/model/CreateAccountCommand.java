package com.paulfernandosr.account.application.model;

import java.math.BigDecimal;

public record CreateAccountCommand(
        String accountNumber,
        String accountType,
        BigDecimal initialBalance,
        String accountStatus,
        String customerIdentification) {
}
