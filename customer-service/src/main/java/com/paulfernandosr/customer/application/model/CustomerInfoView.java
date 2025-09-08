package com.paulfernandosr.customer.application.model;

public record CustomerInfoView(String identification,
                               String name,
                               String gender,
                               Integer age,
                               String address,
                               String phoneNumber,
                               String status) {
}
