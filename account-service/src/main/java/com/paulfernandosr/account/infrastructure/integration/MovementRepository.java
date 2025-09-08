package com.paulfernandosr.account.infrastructure.integration;

import com.paulfernandosr.account.application.provider.MovementManager;
import com.paulfernandosr.account.domain.CustomerIdentification;
import com.paulfernandosr.account.domain.Movement;
import com.paulfernandosr.account.domain.exception.AccountNotFoundException;
import com.paulfernandosr.account.infrastructure.integration.mapper.MovementEntityMapper;
import com.paulfernandosr.account.infrastructure.integration.model.AccountEntity;
import com.paulfernandosr.account.infrastructure.integration.model.MovementEntity;
import com.paulfernandosr.account.infrastructure.integration.repository.AccountPostgresRepository;
import com.paulfernandosr.account.infrastructure.integration.repository.MovementPostgresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovementRepository implements MovementManager {
    private final MovementPostgresRepository movementPostgresRepository;
    private final AccountPostgresRepository accountPostgresRepository;
    private final MovementEntityMapper movementEntityMapper;

    @Override
    public void createMovement(Movement movement) {
        AccountEntity accountEntity = accountPostgresRepository.findByAccountNumber(movement.getAccount().getAccountNumber().value())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        MovementEntity movementEntity = movementEntityMapper.toMovementEntity(movement);
        movementEntity.setAccount(accountEntity);

        movementPostgresRepository.save(movementEntity);
    }

    @Override
    public List<Movement> findMovements(CustomerIdentification customerIdentification, LocalDateTime startDate, LocalDateTime endDate) {
        return movementPostgresRepository.findByCustomerIdentificationAndDateRange(customerIdentification.value(), startDate, endDate)
                .stream()
                .map(movementEntityMapper::toMovement)
                .toList();
    }
}
