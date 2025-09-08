package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.model.UpdateAccountCommand;
import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.application.provider.CustomerManager;
import com.paulfernandosr.account.application.usecase.UpdateAccountUseCase;
import com.paulfernandosr.account.domain.*;
import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAccountService implements UpdateAccountUseCase {
    private final AccountManager accountManager;
    private final CustomerManager customerManager;

    @Override
    public void execute(UpdateAccountCommand updateAccountCommand) {
        AccountNumber accountNumber = new AccountNumber(updateAccountCommand.accountNumber());

        Account account = accountManager.findAccount(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        UpdateAccountCommand.AccountUpdateData accountUpdateData = updateAccountCommand.accountUpdateData();

        CustomerIdentification customerIdentification = new CustomerIdentification(accountUpdateData.customerIdentification());

        Customer customer = customerManager.findCustomer(customerIdentification)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        account.setAccountType(AccountType.valueOf(accountUpdateData.accountType()));
        account.setInitialBalance(new AccountBalance(accountUpdateData.initialBalance()));
        account.setStatus(AccountStatus.valueOf(accountUpdateData.accountStatus()));
        account.setCustomer(customer);

        accountManager.updateAccount(account);
    }
}
