package com.paulfernandosr.customer.application.usecase;

import com.paulfernandosr.customer.application.model.CustomerInfoView;

import java.util.List;

public interface GetAllCustomersUseCase {
    List<CustomerInfoView> execute();
}
