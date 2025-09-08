package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.AccountInfoView;

import java.util.List;

public interface GetAllAccountsUseCase {
    List<AccountInfoView> execute();
}
