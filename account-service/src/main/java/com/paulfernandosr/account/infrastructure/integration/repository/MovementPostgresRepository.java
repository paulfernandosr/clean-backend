package com.paulfernandosr.account.infrastructure.integration.repository;

import com.paulfernandosr.account.infrastructure.integration.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementPostgresRepository extends JpaRepository<MovementEntity, Long> {

    @Query("""
            SELECT m FROM MovementEntity m
            WHERE m.account.customerIdentification = :customerIdentification
            AND m.issuedAt BETWEEN :startDate AND :endDate
            ORDER BY m.issuedAt DESC
            """)
    List<MovementEntity> findByCustomerIdentificationAndDateRange(String customerIdentification, LocalDateTime startDate, LocalDateTime endDate);
}
