package com.farhad.example.functional.salary.v2;

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
    public void SalaryCalculatorTest9() {

        double basicFarhadSalary = 1_000_000D ;
        // Better , but what if  i have to add ANOTHER function?
        double netFarhadSalar = new SalaryCalculatorBuilder() 
                                               .withBonus()
                                               .withTax()
                                               .calculate(basicFarhadSalary); 
        log.info("Basic Salary: {} Net Salary {}",basicFarhadSalary,netFarhadSalar);

    }
}
