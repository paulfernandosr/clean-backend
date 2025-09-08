package com.paulfernandosr.account.infrastructure.integration.repository;

import com.paulfernandosr.account.infrastructure.integration.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountPostgresRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
