package com.farhad.example.functional.bank.v4.reader;


import java.util.function.Function;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function5;
import io.vavr.Lazy;
import io.vavr.Tuple;
import com.farhad.example.functional.bank.v4.Try;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TryReader<R,A> {
    
    private final Function<R,Try<A>> run ;

    public <B> TryReader<R,B> map(Function<A,B> f) {
        return new TryReader<R,B>( (R r) -> apply( r ) 
                                            .map( a -> f.apply( a ) ));
    }

    public <B> TryReader<R,B> mapReader(Function<A,Reader<R,B>> f) {
        return new TryReader<R,B>( (R r) -> apply( r ) 
                                            .map( a -> f.apply( a ).apply( r ) ));
    }

    public <B> TryReader<R,B> flatMap(Function<A,TryReader<R,B>> f) {
        return new TryReader<R,B>( (R r) -> apply( r ) 
                                            .flatMap( a -> f.apply( a ).apply( r ) ));
    }

    public Try<A> apply(R r) {
        return run.apply(r);
    }

}
