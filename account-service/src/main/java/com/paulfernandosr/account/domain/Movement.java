package com.paulfernandosr.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Account account;
    private MovementType movementType;
    private AccountBalance initialBalance;
    private MovementAmount amount;
    private LocalDateTime issuedAt;

    public boolean hasSufficientFunds() {
        return initialBalance.value().compareTo(amount.value()) >= 0;
    }
}
