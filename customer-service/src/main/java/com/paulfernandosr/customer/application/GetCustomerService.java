package com.paulfernandosr.customer.application;

import com.paulfernandosr.customer.application.mapper.CustomerInfoMapper;
import com.paulfernandosr.customer.application.model.CustomerInfoView;
import com.paulfernandosr.customer.application.model.GetCustomerQuery;
import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.application.usecase.GetCustomerUseCase;
import com.paulfernandosr.customer.domain.PersonIdentification;
import com.paulfernandosr.customer.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerService implements GetCustomerUseCase {

    private final CustomerManager customerManager;
    private final CustomerInfoMapper customerInfoMapper;

    @Override
    public CustomerInfoView execute(GetCustomerQuery getCustomerQuery) {
        PersonIdentification personIdentification = new PersonIdentification(getCustomerQuery.customerIdentification());

        return customerManager.findCustomer(personIdentification)
                .map(customerInfoMapper::toCustomerInfoView)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}
