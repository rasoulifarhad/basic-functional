package com.farhad.example.functional02.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class Customer {

    public static final Customer ME = Customer.of(1L);

    private final long customerId ;


    
}
