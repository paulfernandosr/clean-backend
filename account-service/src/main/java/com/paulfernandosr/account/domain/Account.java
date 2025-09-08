package com.paulfernandosr.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private AccountNumber accountNumber;
    private AccountType accountType;
    private AccountBalance initialBalance;
    private AccountStatus status;
    private Customer customer;

    public void updateBalance(Movement movement) {
        BigDecimal newBalance = switch (movement.getMovementType()) {
            case DEPOSIT -> initialBalance.value().add(movement.getAmount().value());
            case WITHDRAWAL -> initialBalance.value().subtract(movement.getAmount().value());
        };

        initialBalance = new AccountBalance(newBalance);
    }
}
