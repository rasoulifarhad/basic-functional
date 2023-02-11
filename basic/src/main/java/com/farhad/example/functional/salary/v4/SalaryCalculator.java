package com.farhad.example.functional.salary.v4;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SalaryCalculator {
    
    private final Function<Double,Double> calc ;

    public SalaryCalculator() {
        this(Function.identity());
        
    }

    private SalaryCalculator(Function<Double,Double> calc) {
        this.calc = calc;
    }

    public SalaryCalculator with(Function<Double,Double> fn) {
        return new SalaryCalculator(this.calc.andThen(fn));
    }

    public double calculate(double basic) {
        return this.calc.apply(basic);
    }
}
