package com.paulfernandosr.account.infrastructure.integration.mapper;

import com.paulfernandosr.account.domain.Movement;
import com.paulfernandosr.account.infrastructure.integration.model.MovementEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AccountEntityMapper.class)
public interface MovementEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "initialBalance", source = "initialBalance.value")
    @Mapping(target = "amount", source = "amount.value")
    MovementEntity toMovementEntity(Movement movement);

    @InheritInverseConfiguration
    Movement toMovement(MovementEntity movementEntity);
}
