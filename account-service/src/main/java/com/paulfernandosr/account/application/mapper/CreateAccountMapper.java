package com.paulfernandosr.account.application.mapper;

import com.paulfernandosr.account.application.model.CreateAccountCommand;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateAccountMapper {

    @Mapping(target = "accountNumber.value", source = "accountNumber")
    @Mapping(target = "initialBalance.value", source = "initialBalance")
    @Mapping(target = "status", source = "accountStatus")
    @Mapping(target = "customer", source = "createAccountCommand")
    Account toAccount(CreateAccountCommand createAccountCommand);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "identification.value", source = "customerIdentification")
    Customer toCustomer(CreateAccountCommand createAccountCommand);
}
