package com.paulfernandosr.account.application.mapper;

import com.paulfernandosr.account.application.model.MovementInfoView;
import com.paulfernandosr.account.domain.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovementInfoMapper {

    @Mapping(target = "customer", source = "account.customer.name.value")
    @Mapping(target = "accountNumber", source = "account.accountNumber.value")
    @Mapping(target = "accountType", source = "account.accountType")
    @Mapping(target = "accountStatus", source = "account.status")
    @Mapping(target = "initialBalance", source = "initialBalance.value")
    @Mapping(target = "amount", source = "movement", qualifiedByName = "toAmount")
    @Mapping(target = "availableBalance", source = "movement", qualifiedByName = "toAvailableBalance")
    MovementInfoView toMovementInfoView(Movement movement);

    @Named("toAmount")
    default BigDecimal toAmount(Movement movement) {
        return switch (movement.getMovementType()) {
            case DEPOSIT -> movement.getAmount().value();
            case WITHDRAWAL -> movement.getAmount().value().negate();
        };
    }

    @Named("toAvailableBalance")
    default BigDecimal toAvailableBalance(Movement movement) {
        return switch (movement.getMovementType()) {
            case DEPOSIT -> movement.getInitialBalance().value().add(movement.getAmount().value());
            case WITHDRAWAL -> movement.getInitialBalance().value().subtract(movement.getAmount().value());
        };
    }

}
