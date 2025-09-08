package com.paulfernandosr.customer.infrastructure.integration.repository;

import com.paulfernandosr.customer.infrastructure.integration.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostgresCustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByPersonIdentification(String identification);
}
