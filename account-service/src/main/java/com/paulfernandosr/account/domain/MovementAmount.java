package com.paulfernandosr.account.domain;

import com.paulfernandosr.account.domain.exception.InvalidAmountException;

import java.math.BigDecimal;

public record MovementAmount(BigDecimal value) {

    public MovementAmount {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Movement cannot be null or less than zero");
        }
    }
}
