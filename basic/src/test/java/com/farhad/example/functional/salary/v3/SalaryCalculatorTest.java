package com.farhad.example.functional.salary.v3;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.stream.Collectors.toList;

@Slf4j
public class SalaryCalculatorTest {
    

    @Test
    public void SalaryCalculatorTest() {
        double basicFarhadSalary = 1_000_000D ;
        double netFarhadSalar = new SalaryCalculator() 
                                               .with(SalaryRules::bonus)
                                               .with(SalaryRules::tax)
                                               .calculate(basicFarhadSalary); 
        log.info("Basic Salary: {} Net Salary {}",basicFarhadSalary,netFarhadSalar);
    }

    @Test
    public void SalaryCalculatorTest2() {
        double basicFarhadSalary = 1_000_000D ;
        double netFarhadSalar = new SalaryCalculator() 
                                               .with(SalaryRules::bonus)
                                               .with(SalaryRules::tax)
                                               .with(s -> s * 0.95)   // regional tax
                                               .calculate(basicFarhadSalary); 
        log.info("Basic Salary: {} Net Salary {}",basicFarhadSalary,netFarhadSalar);
    }

}
