package com.paulfernandosr.customer.infrastructure.integration;

import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.domain.Customer;
import com.paulfernandosr.customer.domain.PersonIdentification;
import com.paulfernandosr.customer.domain.exception.CustomerNotFoundException;
import com.paulfernandosr.customer.infrastructure.integration.entity.CustomerEntity;
import com.paulfernandosr.customer.infrastructure.integration.entity.PersonEntity;
import com.paulfernandosr.customer.infrastructure.integration.mapper.CustomerMapper;
import com.paulfernandosr.customer.infrastructure.integration.repository.PostgresCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository implements CustomerManager {

    private final PostgresCustomerRepository postgresCustomerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void createCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toCustomerEntity(customer);
        postgresCustomerRepository.save(customerEntity);
    }

    @Override
    public void updateCustomer(Customer customer) {
        CustomerEntity customerEntity = postgresCustomerRepository.findByPersonIdentification(customer.getIdentification().value())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        PersonEntity personEntity = customerEntity.getPerson();

        personEntity.setIdentification(customer.getIdentification().value());
        personEntity.setName(customer.getName().value());
        personEntity.setAge(customer.getAge().value());
        personEntity.setAddress(customer.getAddress().value());
        personEntity.setGender(customer.getGender().name());
        personEntity.setPhoneNumber(customer.getPhone().value());

        customerEntity.setActive(customerMapper.toActive(customer.getStatus()));
        customerEntity.setPassword(customer.getPassword().value());

        postgresCustomerRepository.save(customerEntity);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customerEntities = postgresCustomerRepository.findAll();
        return customerMapper.toCustomers(customerEntities);
    }

    @Override
    public Optional<Customer> findCustomer(PersonIdentification identification) {
        return postgresCustomerRepository.findByPersonIdentification(identification.value())
                .map(customerMapper::toCustomer);
    }

    @Override
    public void deleteCustomer(PersonIdentification identification) {
        CustomerEntity customerEntity = postgresCustomerRepository.findByPersonIdentification(identification.value())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customerEntity.setActive(false);

        postgresCustomerRepository.save(customerEntity);
    }
}
