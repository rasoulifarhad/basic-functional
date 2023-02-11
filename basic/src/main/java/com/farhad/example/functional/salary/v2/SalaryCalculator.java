package com.farhad.example.functional.salary.v2;

public class SalaryCalculator {
    
    public double plusAllowance(double d) {
        return d * 1.2;
    }

    public double plusBonus(double d) {
        return d * 1.1;
    }

    public double plusTax(double d) {
        return d * 0.7;
    }

    public double plusSurcharge(double d) {
        return d * 0.9;
    }

    public double calculate(double basic , boolean... bs) {
        double salary = basic;

        if(bs[0]) salary = this.plusAllowance(salary);
        if(bs[1]) salary = this.plusBonus(salary);
        if(bs[2]) salary = this.plusTax(salary);
        if(bs[3]) salary = this.plusSurcharge(salary);
        return salary ;
    }
}
