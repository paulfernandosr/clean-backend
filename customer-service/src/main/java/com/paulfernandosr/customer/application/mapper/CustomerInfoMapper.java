package com.paulfernandosr.customer.application.mapper;

import com.paulfernandosr.customer.application.model.CustomerInfoView;
import com.paulfernandosr.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerInfoMapper {

    @Mapping(target = "identification", source = "identification.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "age", source = "age.value")
    @Mapping(target = "address", source = "address.value")
    @Mapping(target = "phoneNumber", source = "phone.value")
    @Mapping(target = "status", source = "status")
    CustomerInfoView toCustomerInfoView(Customer customer);

    List<CustomerInfoView> toCustomerInfoViews(List<Customer> customers);
}
