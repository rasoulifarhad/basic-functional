package com.farhad.example.functional.caffee.v1;

import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;


public class Cafe {
    
    public Coffee buyCaffee(CreditCard cc) {
        Coffee cup = new Coffee();
        // side-effect
        //how can we test this without contacting the bank or using a mock?
        cc.charge(cup.getCaffeePrice());
        return cup;

    }

    //
    public List<Coffee> buyCaffees(CreditCard cc , int n) {
        return Stream
                     // how can reuse buyCaffee method to buy more caffee without charging the card multiple time?
                    .generate(() -> buyCaffee(cc)) 
                    .limit(n)
                    .collect(toList());
    }
}
