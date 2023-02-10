package com.farhad.example.functional.converter.v2;

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
    public void convertorTest() {

        Converter converter  = new Converter();
        double tenMilesInKm = converter.apply(1.609, 10D) ;
        log.info("{} Miles In Km Is: {}",10,tenMilesInKm);

    }

    @Test
    public void converterUsingCurry1Test() {

        Converter converter  = new Converter();

        Function<Double,Double> mi2KmConverter = converter.curry1(1.609) ;
        double tenMilesInKm = mi2KmConverter.apply(10D);
        log.info("{} Miles In Km Is: {}",10,tenMilesInKm);

    }

    @Test
    public void ListConvertedUsingConverterWithCurry1Test() {

        Converter converter  = new Converter();
        Function<Double,Double> mi2KmConverter = converter.curry1(1.609) ;
        // res: [16.09, 32.18, 80.45]
        List<Double> values = Stream.of(10D,20D,50D)
                                    .map(mi2KmConverter)
                                    .collect(toList());
        log.info("{} Miles In Km Is: {}",Arrays.asList(10D,20D,50D),values);

    }

    @Test
    public void celsius2FahrenheitConversionWithCurryTest() {

        // celsius ---> Fahrenheit : F = C * 9/5 + 32
        // value --> Converter (rate = 9/5) ---> andThen ---> Function (n -> n + 32) --> result
        Converter converter  = new Converter();
        Function<Double,Double> c2fConverter =  converter.curry1(9.0/5).andThen(n -> n+32) ;


        log.info("{} celsius Is {} Fahrenheit",20D,c2fConverter.apply(20D));

    }
    
}
