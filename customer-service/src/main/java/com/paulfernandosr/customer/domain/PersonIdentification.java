package com.paulfernandosr.customer.domain;

import java.util.Objects;

public record PersonIdentification(String value) {
    public PersonIdentification {
        Objects.requireNonNull(value);
    }
}
