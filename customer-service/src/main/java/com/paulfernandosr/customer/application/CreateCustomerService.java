package com.paulfernandosr.customer.application;

import com.paulfernandosr.customer.application.mapper.CreateCustomerMapper;
import com.paulfernandosr.customer.application.model.CreateCustomerCommand;
import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.application.usecase.CreateCustomerUseCase;
import com.paulfernandosr.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerManager customerManager;
    private final CreateCustomerMapper createCustomerMapper;

    @Override
    public void execute(CreateCustomerCommand createCustomerCommand) {
        Customer customer = createCustomerMapper.toCustomer(createCustomerCommand);
        customerManager.createCustomer(customer);
    }
}
