package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.mapper.AccountInfoMapper;
import com.paulfernandosr.account.application.model.AccountInfoView;
import com.paulfernandosr.account.application.model.GetAccountQuery;
import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.application.provider.CustomerManager;
import com.paulfernandosr.account.application.usecase.GetAccountUseCase;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.AccountNumber;
import com.paulfernandosr.account.domain.Customer;
import com.paulfernandosr.account.domain.CustomerIdentification;
import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAccountService implements GetAccountUseCase {
    private final AccountManager accountManager;
    private final AccountInfoMapper accountInfoMapper;
    private final CustomerManager customerManager;

    @Override
    public AccountInfoView execute(GetAccountQuery getAccountQuery) {
        AccountNumber accountNumber = new AccountNumber(getAccountQuery.accountNumber());

        Account account = accountManager.findAccount(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Customer customer = customerManager.findCustomer(account.getCustomer().getIdentification())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        account.setCustomer(customer);

        return accountInfoMapper.toAccountInfoView(account);
    }
}
