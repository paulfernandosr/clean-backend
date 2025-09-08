package com.paulfernandosr.customer.application;

import com.paulfernandosr.customer.application.model.DeleteCustomerCommand;
import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.application.usecase.DeleteCustomerUseCase;
import com.paulfernandosr.customer.domain.PersonIdentification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerService implements DeleteCustomerUseCase {

    private final CustomerManager customerManager;

    @Override
    public void execute(DeleteCustomerCommand deleteCustomerCommand) {
        PersonIdentification personIdentification = new PersonIdentification(deleteCustomerCommand.customerIdentification());
        customerManager.deleteCustomer(personIdentification);
    }
}
