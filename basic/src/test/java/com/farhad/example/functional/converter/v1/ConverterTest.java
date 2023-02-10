package com.farhad.example.functional.converter.v1;

import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ConverterTest {
    

    List<Double> convertValues(List<Double> values,Converter  converter) {
        
        List<Double> convertedValues = new ArrayList<>();

        for(double value : values) {
            convertedValues.add(converter.convert(value));
        }
        return convertedValues;
    }

    @Test
    public void converterTest() {

        List<Double> values = Arrays.asList(10D,20D,50D);
  
        // res: [16.09, 32.18, 80.45]
        List<Double> convertedDistances = convertValues(values, new Mi2KmConverter());
        log.info("Mi:{}  Km:{}",values,convertedDistances);

        //Res: [283.45, 566.9, 1417.25]
        List<Double> convertedWeights = convertValues(values, new Ou2GrConverter());
        log.info("Ou:{}  Gr:{}",values,convertedWeights);
        
    } 
}
