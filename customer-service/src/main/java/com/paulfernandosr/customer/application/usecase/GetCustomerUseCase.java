package com.paulfernandosr.customer.application.usecase;

import com.paulfernandosr.customer.application.model.CustomerInfoView;
import com.paulfernandosr.customer.application.model.GetCustomerQuery;

public interface GetCustomerUseCase {
    CustomerInfoView execute(GetCustomerQuery getCustomerQuery);
}
