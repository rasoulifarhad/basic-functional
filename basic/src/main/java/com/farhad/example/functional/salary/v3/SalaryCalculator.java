package com.farhad.example.functional.salary.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SalaryCalculator {
    
    private final List<Function<Double,Double>> fs = new ArrayList<>();

    public SalaryCalculator with(Function<Double,Double> f) {
        fs.add(f);
        return this ;
    }

    public double calculate(double basic) {
        return fs.stream()
                .reduce(Function.identity(),Function::andThen)
                .apply(basic);

    }
}
