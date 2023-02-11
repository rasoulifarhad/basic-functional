package com.farhad.example.functional.converter.v3;

import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.stream.Collectors.toList;


@Slf4j
public class ConverterTest {
    
    @Test
    public void fahrenheit2CelsiusConversionWithCurryTest() {

        // Fahrenheit ---> celsius : C = (F - 32) * 5/9
        // value -->  Function (n -> n - 32)  --> compose2 --> Converter (rate = 9/5) --> result
        Converter converter  = new Converter();
        Function<Double,Double> f2cConverter =  converter.compose2((Double n) -> n - 32)
                                                        .curry1(5.0/9);


        log.info("{} Fahrenheit Is {} celsius",20D,f2cConverter.apply(20D));

    }
}
