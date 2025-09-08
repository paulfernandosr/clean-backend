package com.paulfernandosr.customer.application.model;

public record UpdateCustomerCommand(
        String identification,
        CustomerUpdateData updateData
) {

    public record CustomerUpdateData(String name,
                                     Integer age,
                                     String address,
                                     String phoneNumber,
                                     String password,
                                     String status) {
    }
}
