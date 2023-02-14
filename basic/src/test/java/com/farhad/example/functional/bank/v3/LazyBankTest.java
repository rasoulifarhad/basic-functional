package com.farhad.example.functional.bank.v3;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function5;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
// import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static com.farhad.example.functional.bank.v3.LazyBankService.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LazyBankTest {
    
    @Test
    public void bank_service_success_test() {

        // Given
        
        //When
        Function<BankConnection,Try<Account>> f =
                (BankConnection conn) -> 
                        open("account_a","123",new BigDecimal( 100.00 ))
                            .apply(conn)
                            .map(acc -> credit(acc,new BigDecimal( 200.00 )).apply(conn))
                            
                            .map(acc -> credit(acc,new BigDecimal( 300.00 )).apply(conn))
                            .flatMap(acc -> debit(acc,new BigDecimal( 400.00 )).apply(conn));
        Try<Account> account = f.apply(new BankConnection());

        // Then
        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isFalse();
    }

    @Test
    public void bank_service_failure_test() {

        // Given

        //When
        Function<BankConnection,Try<Account>> f =
                (BankConnection conn) -> 
                        open("account_a","123",new BigDecimal( 100.00 ))
                            .apply(conn)
                            .map(acc -> credit(acc,new BigDecimal( 200.00 )).apply(conn))
                            
                            .map(acc -> credit(acc,new BigDecimal( 300.00 )).apply(conn))
                            .flatMap(acc -> debit(acc,new BigDecimal( 700.00 )).apply(conn));
        Try<Account> account = f.apply(new BankConnection());

        // Then                
        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isTrue();
    }

}
