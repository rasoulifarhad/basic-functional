package com.farhad.example.functional.salary.v2;

public class SalaryCalculatorBuilder extends SalaryCalculator {

    private boolean hasAllowance = false ;
    private boolean hasBonus = false ;
    private boolean hasTax = false ;
    private boolean hasSurcharge = false ;

    public SalaryCalculatorBuilder withAllowance() {
        this.hasAllowance = true ;
        return this ;
    }
    
    public SalaryCalculatorBuilder withBonus() {
        this.hasBonus = true ;
        return this ;
    }

    public SalaryCalculatorBuilder withTax() {
        this.hasTax = true ;
        return this ;
    }

    public SalaryCalculatorBuilder withSurcharge() {
        this.hasSurcharge = true ;
        return this ;
    }

    public double calculate(double basic) {
        return calculate(basic ,
                         this.hasAllowance,
                         this.hasBonus,
                         this.hasTax,
                         this.hasSurcharge   ); 
    }

}
