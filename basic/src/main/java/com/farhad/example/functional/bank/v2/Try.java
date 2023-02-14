package com.farhad.example.functional.bank.v2;
import java.util.function.*;

public interface Try<A> {
    
    public <B> Try<B> map(Function<A,B> f) ;
    public <B> Try<B> flatMap(Function<A,Try<B>> f) ;
    public boolean isFailure();
}
