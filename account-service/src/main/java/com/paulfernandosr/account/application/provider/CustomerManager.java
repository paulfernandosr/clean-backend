package com.paulfernandosr.account.application.provider;

import com.paulfernandosr.account.domain.Customer;
import com.paulfernandosr.account.domain.CustomerIdentification;

import java.util.Optional;

public interface CustomerManager {
    Optional<Customer> findCustomer(CustomerIdentification customerIdentification);
}
