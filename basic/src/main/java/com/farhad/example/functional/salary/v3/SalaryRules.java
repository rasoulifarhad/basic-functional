package com.farhad.example.functional.salary.v3;

public final class SalaryRules {

    private SalaryRules() {

    }

    public static double allowance(double  d  ) {
        return d * 1.2 ;
    }
    
    public static double bonus(double  d  ) {
        return d * 1.1 ;
    }
    
    public static double tax(double  d  ) {
        return d * 0.7;
    }
    
    public static double surcharge(double  d  ) {
        return d * 0.9 ;
    }
    
}
