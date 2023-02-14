package com.farhad.example.functional.bank.v3;

import java.math.BigDecimal;
import java.util.function.Function;

public class LazyBankService {
    
    public static  Function<BankConnection,Try<Account>> open(String owner,String number,BigDecimal balance ) {
        return ( BankConnection bankConnection ) ->  {
                        if (balance.compareTo(BigDecimal.ZERO ) < 0)
                            return new Failure<>(new InsufficientBalanceException());
                        return new Success<>(new Account(owner,number , new Balance(balance)));
        };

    }

    public static Function<BankConnection,Account> credit(Account account , BigDecimal value) {

        return ( BankConnection bankConnection ) ->  {
            return new Account( account.getNumber(), 
                            account.getNumber(), 
                            new Balance(
                                    account.getBalance().amount.add( value)));
        };
    }

    public static Function<BankConnection,Try<Account>> debit(Account account, BigDecimal value) {

        return ( BankConnection bankConnection ) ->  {
            if (account.getBalance().getAmount().compareTo(value) < 0)
            return new Failure<>(new InsufficientBalanceException());

            return new Success<>( new Account( account.getNumber(), 
                                account.getNumber(), 
                                new Balance(
                                        account.getBalance().amount.subtract( value))));
        };
    }   
}
