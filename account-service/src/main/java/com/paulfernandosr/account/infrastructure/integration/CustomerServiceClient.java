package com.paulfernandosr.account.infrastructure.integration;

import com.paulfernandosr.account.application.provider.CustomerManager;
import com.paulfernandosr.account.domain.Customer;
import com.paulfernandosr.account.domain.CustomerIdentification;
import com.paulfernandosr.account.infrastructure.integration.client.CustomerServiceFeignClient;
import com.paulfernandosr.account.infrastructure.integration.mapper.CustomerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerServiceClient implements CustomerManager {
    private final CustomerServiceFeignClient customerServiceFeignClient;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public Optional<Customer> findCustomer(CustomerIdentification customerIdentification) {
        return customerServiceFeignClient.getCustomerByIdentification(customerIdentification.value())
                .map(customerDtoMapper::toCustomer);
    }
}
