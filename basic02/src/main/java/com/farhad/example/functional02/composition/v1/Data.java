package com.farhad.example.functional02.composition.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List ;

@AllArgsConstructor(staticName = "of")
@Getter
public class Data {
    private final long customerId ;
    private final List<Record> contents ;
}
