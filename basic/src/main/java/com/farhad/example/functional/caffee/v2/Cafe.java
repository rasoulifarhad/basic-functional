package com.farhad.example.functional.caffee.v2;

import java.util.List;
import java.util.function.Function;

import io.vavr.Tuple2;
import io.vavr.collection.Stream;
public class Cafe {
    
    public Tuple2<Coffee,Charge> buyCaffee(CreditCard cc) {

        Coffee cup = new Coffee() ;
        return new Tuple2<Coffee,Charge>(cup, new Charge(cc,cup.getCaffeePrice()));

    }

    public Tuple2<List<Coffee>,Charge> buyCaffees(CreditCard cc ,int n) {
        
        Tuple2<Stream<Coffee>,Stream<Charge>>  purchases = Stream
                                                            .continually(() -> buyCaffee(cc))
                                                            .subSequence(0,n)
                                                            .unzip(Function.identity());
            
         return new Tuple2<>(purchases._1.toJavaList(), 
                             purchases._2.foldLeft(new Charge(cc ,0), Charge::add))   ;
            

    }
}
