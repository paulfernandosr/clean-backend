package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.CreateAccountCommand;

public interface CreateAccountUseCase {
    void execute(CreateAccountCommand createAccountCommand);
}
