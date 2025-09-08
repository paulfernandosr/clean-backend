package com.paulfernandosr.customer.application.model;

public record CreateCustomerCommand(
        String identification,
        String name,
        String gender,
        Integer age,
        String address,
        String phoneNumber,
        String password,
        String status) {
}
