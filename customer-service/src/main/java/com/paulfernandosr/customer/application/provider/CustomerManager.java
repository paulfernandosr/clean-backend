package com.paulfernandosr.customer.application.provider;

import com.paulfernandosr.customer.domain.Customer;
import com.paulfernandosr.customer.domain.PersonIdentification;

import java.util.List;
import java.util.Optional;

public interface CustomerManager {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    List<Customer> findAllCustomers();

    Optional<Customer> findCustomer(PersonIdentification identification);

    void deleteCustomer(PersonIdentification identification);
}
