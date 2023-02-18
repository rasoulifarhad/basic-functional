package com.farhad.example.functional02.remote.option;

import java.util.function.Function;

import cyclops.control.Eval;
import cyclops.control.Maybe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import cyclops.control.Option;

/**
 * 
 ** I/O issues and DataFileMetadata
 *
 * One challenge we may face is that when we atttempt to access the contentds in DataFileMetadata instance for the first time, the remote 
 * server or even local file system may be temporarily unavailable. 
 *
 * This will cause a blow up and an Exception will be thrown that we will have to handle correctly in any client code. 
 *
 * Failure to handle it will result in our application failing.
 *
 * For example if I write some test code that attempts to access content from a fake URL
 *
 *   URLDataFileMetadata meta = new URLDataFileMetadata(10l,"url",new URL("http://xxxxx.com"));
 *   System.out.println(meta.getContents());
 *
 * Running it results in a horrendous stack trace.
 *
 * A hacky way around this may be to attempt to return null from loadContents instead when loading fails, temporarily swapping one 
 * problem for another.
 *
 * Which means any calling code attempting to use the contents of a DataFileMetadata instance has to defensively check for Nulls or 
 * risk runtime exceptions.
 *
 * There is a better option here (pun intended). The JDK does have a type that represents a value that can either be present or 
 * absent — Optional.
 *
 ** What’s wrong with Optional?
 *
 * design APIs and implementations that make illegal states unrepresentable in your code.
 *
 * When we run this code :
 *
 *  Optional.empty().get();
 *
 * we will get a RuntimeException
 *
 * To avoid this we should check if an Optional isPresent before calling get(), which is pretty similar to how we defensively check 
 * for null in our code basis today. 
 *
 * "It would be better if our APIs didn’t expose methods like Optional::get that throw RuntimeExceptions!"
 *
 * Cyclops defines a safe Optional type called Option. Instead of Optional::get you can use overloaded methods such as Option::orElse 
 * to extract a value from the Option type safely.
 *
 * Let’s plugin in Option
 * 
 * The changes to our loadContents implementation are relatively minor, it’s just a case of creating an Option.some instance when a value 
 * is successfully downloaded otherwise returning Option.none
 * 
 * The downside is that we very quickly heading in the direction of generics hell in terms of our variable definition for contents.
 * 
 *  private final Eval<Option<String>> contents = Eval.later( this::loadContents );
 * 
 * One option to improve the signature is to perform a concatMap operation (like flatMap across Iterable types)
 * 
 *   private final Eval<String> contents = Eval.later( this::loadContents )
 *                                                      .concatMap(Function.identity()) ;
 *
 * And that certainly cuts down on the generics.
 * 
 * When we run the failing code, however, contents will resolve to null again as the flatted Option.none is converted into null.
 * 
 *  System.out.println(meta.getContents()
 *                      .get());
 * 
 *  A better alternative is to change the top level type to one which accounts for the fact that it may or may not be present. I.e. 
 *  Make contents an Option.
 * 
 *  But wait —  not to use Optional-like types for fields? 
 * 
 *  We do
 * 
 *  Option as a field
 * 
 * Definitely Maybe
 * 
 * As luck would have it, there is a lazy Option type in Cyclops and (like it’s lazy Haskell cousin) it is called Maybe
 * 
 * Maybe allows us to create an Option when we don’t know whether it will ultimately be a Some or a None at creation time. 
 * 
 *      private final Option<String> contents = Maybe.fromEval( Eval.later( this::loadContents ))
 *                                                   .concatMap(Function.identity()) ;
 *
 * 
 * 
 *   Now we have a contents variable that
 * 
 *    1. Is lazily populated on first access
 *    2. Is only populated once due to Memoization
 *    3. Does not throw Exceptions if the I/O data is unavailable
 *    4. Represents the fact the contents data maybe present or absent
 * 
 *  Do we still want memoization?
 * 
 * It is possible, that due to the fact loading may fail that we may prefer to load the contents afresh each time we access 
 * it. In that case we can switch
 * 
 *    Eval.later
 * To
 *    Eval.always
 * 
 *  Which turns off caching.
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
