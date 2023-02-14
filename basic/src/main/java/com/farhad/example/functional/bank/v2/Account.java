package com.farhad.example.functional.bank.v2;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class Account {
    private final String  owner ;
    private final String  number ;
    private final Balance balance;

    public Account credit(BigDecimal value){
        return new Account(owner, number, new Balance(balance.amount.add(value)));
    }

    public Try<Account> debit(BigDecimal value) {

        if (balance.amount.compareTo(value ) < 0)
            return new Failure<>(new InsufficientBalanceException());

        return new Success<>(new Account(owner, number, new Balance(balance.amount.subtract(value)))) ;

        
    }

}
