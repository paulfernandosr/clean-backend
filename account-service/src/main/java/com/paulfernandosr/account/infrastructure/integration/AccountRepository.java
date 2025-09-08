package com.paulfernandosr.account.infrastructure.integration;

import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.AccountNumber;
import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.infrastructure.integration.model.AccountEntity;
import com.paulfernandosr.account.infrastructure.integration.mapper.AccountEntityMapper;
import com.paulfernandosr.account.infrastructure.integration.repository.AccountPostgresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements AccountManager {

    private final AccountPostgresRepository accountPostgresRepository;
    private final AccountEntityMapper accountEntityMapper;

    @Override
    public void createAccount(Account account) {
        AccountEntity accountEntity = accountEntityMapper.toAccountEntity(account);
        accountPostgresRepository.save(accountEntity);
    }

    @Override
    public void updateAccount(Account account) {
        AccountEntity accountEntity = accountPostgresRepository.findByAccountNumber(account.getAccountNumber().value())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        accountEntity.setAccountNumber(account.getAccountNumber().value());
        accountEntity.setAccountType(account.getAccountType().name());
        accountEntity.setActive(accountEntityMapper.toActive(account.getStatus()));
        accountEntity.setInitialBalance(account.getInitialBalance().value());
        accountEntity.setCustomerIdentification(account.getCustomer().getIdentification().value());

        accountPostgresRepository.save(accountEntity);
    }

    @Override
    public Optional<Account> findAccount(AccountNumber accountNumber) {
        return accountPostgresRepository.findByAccountNumber(accountNumber.value())
                .map(accountEntityMapper::toAccount);
    }

    @Override
    public List<Account> findAllAccounts() {
        List<AccountEntity> accountEntities = accountPostgresRepository.findAll();
        return accountEntityMapper.toAccounts(accountEntities);
    }
}
