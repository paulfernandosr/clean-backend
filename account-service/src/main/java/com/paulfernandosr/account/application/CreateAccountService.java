package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.mapper.CreateAccountMapper;
import com.paulfernandosr.account.application.model.CreateAccountCommand;
import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.application.provider.CustomerManager;
import com.paulfernandosr.account.application.usecase.CreateAccountUseCase;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.Customer;
import com.paulfernandosr.account.domain.CustomerIdentification;
import com.paulfernandosr.account.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private final CustomerManager customerManager;
    private final AccountManager accountManager;
    private final CreateAccountMapper createAccountMapper;

    @Override
    public void execute(CreateAccountCommand createAccountCommand) {
        Account account = createAccountMapper.toAccount(createAccountCommand);

        Customer customer = customerManager.findCustomer(new CustomerIdentification(createAccountCommand.customerIdentification()))
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        account.setCustomer(customer);

        accountManager.createAccount(account);
    }
}
