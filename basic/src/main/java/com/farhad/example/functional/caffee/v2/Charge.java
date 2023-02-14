package com.farhad.example.functional.caffee.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Charge {
    
    private CreditCard creditCard ;
    private int amount ;

    public Charge add(Charge other) {
        if (creditCard == other.getCreditCard())
            return new Charge(creditCard, amount + other.getAmount());
        else 
            throw new RuntimeException("Can not combine charges to diferent  cards");
    }

}
