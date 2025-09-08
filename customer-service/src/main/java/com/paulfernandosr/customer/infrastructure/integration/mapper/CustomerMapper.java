package com.paulfernandosr.customer.infrastructure.integration.mapper;

import com.paulfernandosr.customer.domain.Customer;
import com.paulfernandosr.customer.domain.CustomerStatus;
import com.paulfernandosr.customer.infrastructure.integration.entity.CustomerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password.value")
    @Mapping(target = "active", source = "status")
    @Mapping(target = "person.name", source = "name.value")
    @Mapping(target = "person.gender", source = "gender")
    @Mapping(target = "person.age", source = "age.value")
    @Mapping(target = "person.identification", source = "identification.value")
    @Mapping(target = "person.address", source = "address.value")
    @Mapping(target = "person.phoneNumber", source = "phone.value")
    CustomerEntity toCustomerEntity(Customer customer);

    List<Customer> toCustomers(List<CustomerEntity> customerEntities);

    @InheritInverseConfiguration
    Customer toCustomer(CustomerEntity customerEntity);

    default boolean toActive(CustomerStatus customerStatus) {
        return switch (customerStatus) {
            case ACTIVE -> true;
            case INACTIVE -> false;
        };
    }

    default CustomerStatus toStatus(boolean active) {
        return active ? CustomerStatus.ACTIVE : CustomerStatus.INACTIVE;
    }
}
