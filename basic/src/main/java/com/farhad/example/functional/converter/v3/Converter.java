package com.farhad.example.functional.converter.v3;

public class Converter implements ExtendedBiFunction<Double,Double,Double>{

    @Override
    public Double apply(Double conversionRate, Double value) {
        return value * conversionRate ;
    } 
    
    
}
