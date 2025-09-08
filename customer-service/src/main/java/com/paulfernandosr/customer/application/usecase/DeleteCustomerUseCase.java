package com.paulfernandosr.customer.application.usecase;

import com.paulfernandosr.customer.application.model.DeleteCustomerCommand;

public interface DeleteCustomerUseCase {
    void execute(DeleteCustomerCommand deleteCustomerCommand);
}
