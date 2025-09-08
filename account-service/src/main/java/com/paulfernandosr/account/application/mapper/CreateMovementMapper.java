package com.paulfernandosr.account.application.mapper;

import com.paulfernandosr.account.application.model.CreateMovementCommand;
import com.paulfernandosr.account.domain.Movement;
import com.paulfernandosr.account.domain.MovementAmount;
import com.paulfernandosr.account.domain.MovementType;
import com.paulfernandosr.account.domain.exception.InvalidAmountException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateMovementMapper {

    @Mapping(target = "issuedAt", ignore = true)
    @Mapping(target = "initialBalance", ignore = true)
    @Mapping(target = "movementType", source = "amount")
    @Mapping(target = "account.accountNumber.value", source = "accountNumber")
    Movement toMovement(CreateMovementCommand createMovementCommand);

    default MovementType toMovementType(BigDecimal amount) {
        return switch (amount.signum()) {
            case 1 -> MovementType.DEPOSIT;
            case -1 -> MovementType.WITHDRAWAL;
            default -> throw new InvalidAmountException("Amount cannot be zero");
        };
    }

    default MovementAmount toAmount(BigDecimal amount) {
        return new MovementAmount(amount.abs());
    }
}
