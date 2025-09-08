package com.paulfernandosr.account.application.mapper;

import com.paulfernandosr.account.application.model.AccountInfoView;
import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountInfoMapper {

    @Mapping(target = "accountNumber", source = "accountNumber.value")
    @Mapping(target = "initialBalance", source = "initialBalance.value")
    AccountInfoView toAccountInfoView(Account account);

    @Mapping(target = "identification", source = "identification.value")
    @Mapping(target = "name", source = "name.value")
    AccountInfoView.CustomerData toCustomerData(Customer customer);

    List<AccountInfoView> toAccountInfoViews(List<Account> accounts);
}
