package com.paulfernandosr.account.application.model;

import java.math.BigDecimal;

public record UpdateAccountCommand(String accountNumber, AccountUpdateData accountUpdateData) {

    public record AccountUpdateData(String accountType,
                                    BigDecimal initialBalance,
                                    String accountStatus,
                                    String customerIdentification) {
    }
}
