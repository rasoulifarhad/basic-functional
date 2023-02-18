package com.farhad.example.functional02.remote.option;


import lombok.AllArgsConstructor;
import lombok.Getter;
import cyclops.data.LazySeq; 


@AllArgsConstructor(staticName = "of")
@Getter
public class Data {
    private final long customerId ;
    private final LazySeq<Record> contents ;
}
