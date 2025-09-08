package com.paulfernandosr.customer.application;

import com.paulfernandosr.customer.application.model.UpdateCustomerCommand;
import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.application.usecase.UpdateCustomerUseCase;
import com.paulfernandosr.customer.domain.*;
import com.paulfernandosr.customer.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerService implements UpdateCustomerUseCase {

    private final CustomerManager customerManager;

    @Override
    public void execute(UpdateCustomerCommand updateCustomerCommand) {
        PersonIdentification personIdentification = new PersonIdentification(updateCustomerCommand.identification());

        Customer customer = customerManager.findCustomer(personIdentification)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        UpdateCustomerCommand.CustomerUpdateData customerUpdateData = updateCustomerCommand.updateData();

        customer.setName(new PersonName(customerUpdateData.name()));
        customer.setAge(new PersonAge(customerUpdateData.age()));
        customer.setAddress(new PersonAddress(customerUpdateData.address()));
        customer.setPhone(new PersonPhone(customerUpdateData.phoneNumber()));
        customer.setPassword(new CustomerPassword(customerUpdateData.password()));
        customer.setStatus(CustomerStatus.valueOf(customerUpdateData.status()));

        customerManager.updateCustomer(customer);
    }
}
