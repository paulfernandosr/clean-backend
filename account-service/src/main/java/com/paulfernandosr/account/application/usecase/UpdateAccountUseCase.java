package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.UpdateAccountCommand;

public interface UpdateAccountUseCase {
    void execute(UpdateAccountCommand updateAccountCommand);
}
