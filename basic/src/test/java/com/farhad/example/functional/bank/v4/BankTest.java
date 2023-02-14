package com.farhad.example.functional.bank.v4;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.farhad.example.functional.bank.v4.reader.TryReader;

import static com.farhad.example.functional.bank.v4.BankService.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BankTest {
    
    @Test
    public void bank_service_success_test() { 

        // Given
        
        //When
        TryReader<BankConnection,Account> reader =
                        open("account_a","123",new BigDecimal( 100.00 ))
                            .mapReader( acc -> credit( acc,  new BigDecimal( 200.00 ) ) )
                            .mapReader( acc -> credit( acc,  new BigDecimal(300.00 ) ) )
                            .flatMap( acc -> debit(acc,new BigDecimal( 400.00 )) );
        Try<Account> account = reader.apply(new BankConnection());

        // Then
        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isFalse();
    }

    @Test
    public void bank_service_failure_test() {

        // Given

        //When
        TryReader<BankConnection,Account> reader =
                        open("account_a","123",new BigDecimal( 100.00 ))
                            .mapReader( acc -> credit( acc,  new BigDecimal( 200.00 ) ) )
                            .mapReader( acc -> credit( acc,  new BigDecimal(300.00 ) ) )
                            .flatMap( acc -> debit(acc,new BigDecimal( 700.00 )) );
        Try<Account> account = reader.apply(new BankConnection());

        // Then                
        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isTrue();
    }
}
