package com.farhad.example.functional.bank.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BankTest {
    
    @Test
    public void bank_test() {

        // Given
        Account a = new Account("a_account", "123");
        Account b = new Account("b_account", "456");
        Account c = new Account("c_account", "789");

        // When
        List<Account> unpaid = new ArrayList<>();

        for(Account account : Arrays.asList(a,b,c)) {
            try {
                account.debit(new BigDecimal(100.00));
            } catch(InsufficientBalanceException ex) {
                unpaid.add(account);
            }
        }

        log.info("unpaid: {}",unpaid);
        assertThat(unpaid).contains(a,b,c);
    }

    @Test
    public void another_bank_test() {

        // Given
        Account a = new Account("a_account", "123");
        Account b = new Account("b_account", "456");
        Account c = new Account("c_account", "789");

        // When
        List<Account> unpaid = new ArrayList<>();

        Stream.of(a,b,c).forEach(account -> {
            try {
                account.debit(new BigDecimal(100.00));
            } catch(InsufficientBalanceException ex) {
                unpaid.add(account);
            }

        });
        log.info("unpaid: {}",unpaid);
        assertThat(unpaid).contains(a,b,c);

    }


}
