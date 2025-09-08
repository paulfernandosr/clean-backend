package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.mapper.CreateMovementMapper;
import com.paulfernandosr.account.application.model.CreateMovementCommand;
import com.paulfernandosr.account.application.provider.AccountManager;
import com.paulfernandosr.account.application.provider.MovementManager;
import com.paulfernandosr.account.application.usecase.CreateMovementUseCase;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.Movement;
import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.domain.exception.InvalidAmountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateMovementService implements CreateMovementUseCase {
    private final AccountManager accountManager;
    private final MovementManager movementManager;
    private final CreateMovementMapper createMovementMapper;

    @Override
    @Transactional
    public void execute(CreateMovementCommand createMovementCommand) {
        Movement movement = createMovementMapper.toMovement(createMovementCommand);

        Account account = accountManager.findAccount(movement.getAccount().getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        movement.setAccount(account);
        movement.setInitialBalance(account.getInitialBalance());
        movement.setIssuedAt(LocalDateTime.now());

        if (!movement.hasSufficientFunds()) {
            throw new InvalidAmountException("Insufficient funds");
        }

        movementManager.createMovement(movement);

        account.updateBalance(movement);

        accountManager.updateAccount(account);
    }
}
