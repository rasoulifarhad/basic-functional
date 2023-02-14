package com.farhad.example.functional.bank.v1;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class Account {
    private final String  owner ;
    private final String  number ;
    private Balance balance = new Balance(BigDecimal.ZERO);

    public void credit(BigDecimal value){
        balance = new Balance(balance.amount.add(value));
    }

    public void debit(BigDecimal value) throws InsufficientBalanceException{

        if (balance.amount.compareTo(value ) < 0)
            throw new  InsufficientBalanceException();

        balance = new Balance(balance.amount.subtract(value));

        
    }

}
