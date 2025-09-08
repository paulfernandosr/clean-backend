package com.paulfernandosr.account.infrastructure.integration.mapper;

import com.paulfernandosr.account.domain.Customer;
import com.paulfernandosr.account.infrastructure.integration.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerDtoMapper {

    @Mapping(target = "identification.value", source = "identification")
    @Mapping(target = "name.value", source = "name")
    Customer toCustomer(CustomerDto customerDto);
}
