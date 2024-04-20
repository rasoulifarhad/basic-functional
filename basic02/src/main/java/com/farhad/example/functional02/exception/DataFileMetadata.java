package com.farhad.example.functional02.exception;

import java.util.function.Function;

import cyclops.control.Eval;
import cyclops.control.Maybe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import cyclops.control.Option;

/**
 *  No Exceptions
 * 
 *  
 */
@Getter
@AllArgsConstructor
public abstract class DataFileMetadata {
    
    private final long customerId ;
    private final String type ;

    // private final Eval<Option<String>> contents = Eval.later( this::loadContents );
 
    // private final Eval<String> contents = Eval.later( this::loadContents )
    //                                                    .concatMap(Function.identity()) ;

    private final Option<String> contents = Maybe.fromEval( Eval.later( this::loadContents ))
                                                 .concatMap(Function.identity()) ;
    public abstract Option<String> loadContents() ;
}
