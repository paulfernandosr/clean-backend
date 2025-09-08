package com.paulfernandosr.account.application.provider;

import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.AccountNumber;

import java.util.List;
import java.util.Optional;

public interface AccountManager {
    void createAccount(Account account);

    void updateAccount(Account account);

    Optional<Account> findAccount(AccountNumber accountNumber);

    List<Account> findAllAccounts();
}
