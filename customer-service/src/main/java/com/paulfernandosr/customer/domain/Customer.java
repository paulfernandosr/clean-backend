package com.paulfernandosr.customer.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    private CustomerPassword password;
    private CustomerStatus status;
}
