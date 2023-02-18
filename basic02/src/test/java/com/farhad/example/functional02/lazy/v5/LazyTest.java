package com.farhad.example.functional02.lazy.v5;

import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LazyTest {
    
    int called = 0;

    @Test
    public void Memoization_test() {

        Supplier<Integer> lazyChaching = Memoization.memoizeSupplier( () -> called++ );

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        assertThat(lazyChaching.get()).isEqualTo(0);
    }
}
