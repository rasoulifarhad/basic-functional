package com.farhad.example.functional.salary.v1;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

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

        // How can I remember the right sequence?
        double netFarhadSalar = new SalaryCalculator()
                                            .calculate(basicFarhadSalary,
                                                     false,  // allowance
                                                     true,         // bonus
                                                     true,         // tax
                                                     false);       // surchange

        log.info("Basic Salary: {} Net Salary {}",basicFarhadSalary,netFarhadSalar);

    }
}
