package com.paulfernandosr.account.infrastructure.integration.client;

import com.paulfernandosr.account.infrastructure.integration.model.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service")
public interface CustomerServiceFeignClient {

    @GetMapping("/customers/{identification}")
    Optional<CustomerDto> getCustomerByIdentification(@PathVariable String identification);
}
