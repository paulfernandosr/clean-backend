package com.paulfernandosr.customer.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private PersonIdentification identification;
    private PersonName name;
    private PersonGender gender;
    private PersonAge age;
    private PersonAddress address;
    private PersonPhone phone;
}
