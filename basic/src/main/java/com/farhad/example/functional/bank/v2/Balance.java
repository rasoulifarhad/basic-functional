package com.farhad.example.functional.bank.v2;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Balance {

    final BigDecimal amount;
}
