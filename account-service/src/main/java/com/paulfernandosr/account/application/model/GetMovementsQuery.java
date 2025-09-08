package com.paulfernandosr.account.application.model;

import java.time.LocalDate;

public record GetMovementsQuery(
        String customerIdentification,
        LocalDate startDate,
        LocalDate endDate) {
}
