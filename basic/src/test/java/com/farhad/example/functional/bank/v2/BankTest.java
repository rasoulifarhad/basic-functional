package com.farhad.example.functional.bank.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import static com.farhad.example.functional.bank.v2.BankService.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BankTest {
   
    @Test
    public void bank_test() {

        // Given
        Account a = new Account("a_account", "123", new Balance(BigDecimal.ZERO));
        Account b = new Account("b_account", "456",new Balance(BigDecimal.ZERO));
        Account c = new Account("c_account", "789",new Balance(BigDecimal.ZERO));

        // When
        List<Account> unpaid =
                                Stream.of(a,b,c)
                                        .map(account -> new Tuple2<>(account,
                                                                    account.debit(new BigDecimal(100.00))))
                                        .filter(t -> t._2.isFailure())      
                                        .map(t -> t._1)
                                        .collect(Collectors.toList());
        log.info("unpaid: {}",unpaid);
        assertThat(unpaid).contains(a,b,c);
    }

    @Test
    public void another_bank_test() {

        // Given
        Account a = new Account("a_account", "123", new Balance(BigDecimal.ZERO));
        Account b = new Account("b_account", "456",new Balance(BigDecimal.ZERO));
        Account c = new Account("c_account", "789",new Balance(BigDecimal.ZERO));

        // When
        List<Account> unpaid =
                                Stream.of(a,b,c)
                                        // .map(account -> new Tuple2<>(account,
                                        //                             account.debit(new BigDecimal(100.00))))
                                        .filter(account -> account.debit(new BigDecimal(100.00))
                                                             .isFailure()        )      
                                        .collect(Collectors.toList());
        log.info("unpaid: {}",unpaid);
        assertThat(unpaid).contains(a,b,c);
    }

    @Test
    public void bank_service_success_test() {

        Try<Account> account = 
                        open("account_a","123",new BigDecimal( 100.00 ));

        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isFalse();
    }

    @Test
    public void bank_service_failure_test() {

        Try<Account> account = 
                        open("account_a","123",new BigDecimal( -100.00 ));

        log.info("Account failed: {}" ,account.isFailure());

        assertThat(account.isFailure()).isTrue();
    }

}
