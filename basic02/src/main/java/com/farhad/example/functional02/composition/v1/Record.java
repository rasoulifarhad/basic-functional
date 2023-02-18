package com.farhad.example.functional02.composition.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class Record {
    private final long recordId ;
    private final String record ;
}
