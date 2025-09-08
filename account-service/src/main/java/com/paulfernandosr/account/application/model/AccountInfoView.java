package com.paulfernandosr.account.application.model;

import java.math.BigDecimal;

public record AccountInfoView(
        String accountNumber,
        String accountType,
        BigDecimal initialBalance,
        String status,
        CustomerData customer) {

    public record CustomerData(String identification, String name) {
    }
}
