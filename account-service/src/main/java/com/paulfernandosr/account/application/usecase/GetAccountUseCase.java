package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.AccountInfoView;
import com.paulfernandosr.account.application.model.GetAccountQuery;

public interface GetAccountUseCase {
    AccountInfoView execute(GetAccountQuery getAccountQuery);
}
