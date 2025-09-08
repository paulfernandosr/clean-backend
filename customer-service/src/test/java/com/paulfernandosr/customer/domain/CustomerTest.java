package com.paulfernandosr.customer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerTest {

    @Test
    void shouldReturnCustomerWhenPersonIsValid() {
        Customer customer = new Customer();
        customer.setIdentification(new PersonIdentification("12345678"));
        customer.setName(new PersonName("Paul Fernando"));
        customer.setGender(PersonGender.MALE);
        customer.setAge(new PersonAge(27));
        customer.setAddress(new PersonAddress("Jr. el Hierro"));
        customer.setPhone(new PersonPhone("992857645"));
        customer.setPassword(new CustomerPassword("mypa$$"));
        customer.setStatus(CustomerStatus.ACTIVE);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

}