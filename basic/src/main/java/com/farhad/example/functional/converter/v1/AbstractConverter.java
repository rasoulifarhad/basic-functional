package com.farhad.example.functional.converter.v1;

public abstract class AbstractConverter implements Converter {

    @Override
    public double convert(double value) {
        return value * getConversionRate();
    }
    
    public abstract double getConversionRate() ;
}
