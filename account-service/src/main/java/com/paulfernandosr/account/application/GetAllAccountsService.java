package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.mapper.AccountInfoMapper;
import com.paulfernandosr.account.application.model.AccountInfoView;
import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.application.usecase.GetAllAccountsUseCase;
import com.paulfernandosr.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAccountsService implements GetAllAccountsUseCase {
    private final AccountManager accountManager;
    private final AccountInfoMapper accountInfoMapper;

    @Override
    public List<AccountInfoView> execute() {
        List<Account> accounts = accountManager.findAllAccounts();
        return accountInfoMapper.toAccountInfoViews(accounts);
    }
}
