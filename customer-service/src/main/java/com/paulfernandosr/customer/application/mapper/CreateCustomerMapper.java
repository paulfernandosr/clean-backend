package com.paulfernandosr.customer.application.mapper;

import com.paulfernandosr.customer.application.model.CreateCustomerCommand;
import com.paulfernandosr.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateCustomerMapper {

    @Mapping(target = "identification.value", source = "identification")
    @Mapping(target = "name.value", source = "name")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "age.value", source = "age")
    @Mapping(target = "address.value", source = "address")
    @Mapping(target = "phone.value", source = "phoneNumber")
    @Mapping(target = "password.value", source = "password")
    @Mapping(target = "status", source = "status")
    Customer toCustomer(CreateCustomerCommand createCustomerCommand);
}
