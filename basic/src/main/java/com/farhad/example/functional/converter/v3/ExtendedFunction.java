package com.farhad.example.functional.converter.v3;

import java.util.function.Function;

@FunctionalInterface
public interface ExtendedFunction<T,R> extends   Function<T,R> {
   
    default <V>  Function<V,R> compose1(Function<? super V, ? extends T> before) {
        return (V v) ->apply(before.apply(v));
    }

    
}
