package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.CreateMovementCommand;

public interface CreateMovementUseCase {
    void execute(CreateMovementCommand createMovementCommand);
}
