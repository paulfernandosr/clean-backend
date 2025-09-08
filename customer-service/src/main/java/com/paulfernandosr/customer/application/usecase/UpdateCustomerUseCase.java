package com.paulfernandosr.customer.application.usecase;

import com.paulfernandosr.customer.application.model.UpdateCustomerCommand;

public interface UpdateCustomerUseCase {
    void execute(UpdateCustomerCommand updateCustomerCommand);
}
