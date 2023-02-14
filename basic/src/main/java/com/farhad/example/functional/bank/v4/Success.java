package com.farhad.example.functional.bank.v4;

import java.util.function.Function;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Success<A> implements Try<A>{

    private final A value;

    @Override
    public <B> Try<B> flatMap(Function<A, Try<B>> f) {
        return f.apply(value);
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public <B> Try<B> map(Function<A, B> f) {
        return new Success<>(f.apply(value)); 
    }
    
}
