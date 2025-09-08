package com.paulfernandosr.account.application.provider;

import com.paulfernandosr.account.domain.CustomerIdentification;
import com.paulfernandosr.account.domain.Movement;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementManager {
    void createMovement(Movement movement);

    List<Movement> findMovements(CustomerIdentification customerIdentification, LocalDateTime startDate, LocalDateTime endDate);
}
