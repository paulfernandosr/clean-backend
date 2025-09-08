package com.paulfernandosr.account.infrastructure.integration.mapper;

import com.paulfernandosr.account.domain.Account;
import com.paulfernandosr.account.domain.AccountStatus;
import com.paulfernandosr.account.infrastructure.integration.model.AccountEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountNumber", source = "accountNumber.value")
    @Mapping(target = "initialBalance", source = "initialBalance.value")
    @Mapping(target = "customerIdentification", source = "customer.identification.value")
    @Mapping(target = "active", source = "status")
    AccountEntity toAccountEntity(Account account);

    @InheritInverseConfiguration
    Account toAccount(AccountEntity accountEntity);

    List<Account> toAccounts(List<AccountEntity> accountEntity);

    default boolean toActive(AccountStatus accountStatus) {
        return switch (accountStatus) {
            case ACTIVE -> true;
            case INACTIVE -> false;
        };
    }

    default AccountStatus toStatus(boolean active) {
        return active ? AccountStatus.ACTIVE : AccountStatus.INACTIVE;
    }
}
