package com.farhad.example.functional.bank.v3;


import java.util.function.Function;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Failure<A> implements Try<A>  {
    
    private final Object error; 

    @SuppressWarnings("unchecked")
    @Override
    public <B> Try<B> flatMap(Function<A, Try<B>> f) {
        return (Failure<B>)this;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <B> Try<B> map(Function<A, B> f) {
        return (Failure<B>)this;
    }
}
