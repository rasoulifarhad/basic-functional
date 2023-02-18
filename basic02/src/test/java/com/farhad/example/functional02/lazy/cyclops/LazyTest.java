package com.farhad.example.functional02.lazy.cyclops;


import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import cyclops.control.Eval;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LazyTest {
    
    int called = 0;

    @Test
    public void Memoization_test() {

        Eval<Integer> lazyChaching = Eval.later( () -> called++ );

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        log.info("Calling get on lazyCaching: {}" ,lazyChaching.get());

        assertThat(lazyChaching.get()).isEqualTo(0);
    }
}
