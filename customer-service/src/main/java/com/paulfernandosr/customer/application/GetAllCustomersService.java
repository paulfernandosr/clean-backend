package com.paulfernandosr.customer.application;

import com.paulfernandosr.customer.application.mapper.CustomerInfoMapper;
import com.paulfernandosr.customer.application.model.CustomerInfoView;
import com.paulfernandosr.customer.application.provider.CustomerManager;
import com.paulfernandosr.customer.application.usecase.GetAllCustomersUseCase;
import com.paulfernandosr.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCustomersService implements GetAllCustomersUseCase {

    private final CustomerManager customerManager;
    private final CustomerInfoMapper customerInfoMapper;

    @Override
    public List<CustomerInfoView> execute() {
        List<Customer> customers = customerManager.findAllCustomers();
        return customerInfoMapper.toCustomerInfoViews(customers);
    }
}
