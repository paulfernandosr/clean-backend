package com.paulfernandosr.account.application.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementInfoView(
        LocalDateTime issuedAt,
        String customer,
        String accountNumber,
        String accountType,
        String accountStatus,
        BigDecimal initialBalance,
        BigDecimal amount,
        BigDecimal availableBalance
) {
}
