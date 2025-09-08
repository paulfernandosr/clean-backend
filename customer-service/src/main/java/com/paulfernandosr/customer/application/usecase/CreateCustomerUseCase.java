package com.paulfernandosr.customer.application.usecase;

import com.paulfernandosr.customer.application.model.CreateCustomerCommand;

public interface CreateCustomerUseCase {
    void execute(CreateCustomerCommand createCustomerCommand);
}
