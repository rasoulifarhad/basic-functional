package com.farhad.example.functional.bank.v2;

import java.math.BigDecimal;

public class BankService {
 
     
    public static Try<Account> open(String owner,String number,BigDecimal balance) {
        
        if (balance.compareTo(BigDecimal.ZERO ) < 0)
            return new Failure<>(new InsufficientBalanceException());
        return new Success<>(new Account(owner,number , new Balance(balance)));

    }

    public static Account credit(Account account , BigDecimal value) {

        return new Account( account.getNumber(), 
                            account.getNumber(), 
                            new Balance(
                                    account.getBalance().amount.add( value)));
    }

    public static Try<Account> debit(Account account, BigDecimal value) {

        if (account.getBalance().getAmount().compareTo(value) < 0)
            return new Failure<>(new InsufficientBalanceException());

        return new Success<>( new Account( account.getNumber(), 
                            account.getNumber(), 
                            new Balance(
                                    account.getBalance().amount.subtract( value))));

    }   
}
