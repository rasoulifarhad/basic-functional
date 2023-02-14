package com.farhad.example.functional.bank.v4;


import java.math.BigDecimal;

import com.farhad.example.functional.bank.v4.reader.Reader;
import com.farhad.example.functional.bank.v4.reader.TryReader;

public class BankService {
 
     
    public static  TryReader<BankConnection,Account> open(String owner,String number,BigDecimal balance ) {
        return new TryReader<BankConnection,Account>( 
            ( BankConnection bankConnection ) ->  
                (balance.compareTo(BigDecimal.ZERO ) < 0) ? new Failure<>( new InsufficientBalanceException() ) 
                                                          :  new Success<>( new Account( owner,number , new Balance( balance ) ) ) ) ;
    }

    public static Reader<BankConnection,Account> credit(Account account , BigDecimal value) {

        return new Reader<BankConnection,Account>( 
            ( BankConnection bankConnection ) ->  
                                    new Account( account.getNumber(), 
                                                 account.getNumber(), 
                                                 new Balance(
                                                            account.getBalance().amount.add( value)) ) );
    }

    public static TryReader<BankConnection,Account> debit(Account account, BigDecimal value) {

        return new TryReader<BankConnection,Account>( 
            ( BankConnection bankConnection ) ->  
                ( account.getBalance().getAmount().compareTo(value) < 0 ) 
                                            ? new Failure<>( new InsufficientBalanceException() ) 
                                            :  new Success<>( new Account(  account.getNumber(),
                                                                            account.getNumber() , 
                                                                            new Balance( account.getBalance().amount.subtract( value ) ) ) ) )  ;

    }   
}
