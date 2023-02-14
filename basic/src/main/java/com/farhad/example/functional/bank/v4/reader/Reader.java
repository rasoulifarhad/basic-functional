package com.farhad.example.functional.bank.v4.reader;

import java.util.function.Function;

import lombok.RequiredArgsConstructor;

// The reader monad provides an environment to wrap an abstraction without evaluating it

@RequiredArgsConstructor
public class Reader<R,A> {
    
    private final Function<R,A> run ;

    public <B> Reader<R,B> map(Function<A,B> f) {
        return new Reader<>( (R r) -> f.apply(apply(r)) );
    }


    public <B> Reader<R,B> flatMap(Function<A,Reader<R,B>> f) {
        return new Reader<>( (R r) -> f.apply( apply(r) ).apply( r ) );
    }

    public A apply(R r) {
        return run.apply(r);
    }

}
