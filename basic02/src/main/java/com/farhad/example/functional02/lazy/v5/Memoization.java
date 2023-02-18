package com.farhad.example.functional02.lazy.v5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Any Supplier that has passed through the MemoizeSupplier method automatically caches it’s result.
 * 
 *  int called = 0;
 *  Supplier<Integer> lazyCached = memoizeSupplier( ()- > called++ );
 * 
 * Will always return 0 , no matter how often get() is called and called will always remain 1 once 
 * get() has been called at least once.
 * 
 * https://github.com/aol/cyclops-react
 * 
 * The cyclops library for functional programming in Java provides an (more efficient) implementation 
 * for us and we can add it to our Maven our Gradle class paths
 * 
 *   <dependency>
 *      <groupId>com.oath.cyclops</groupId>
 *      <artifactId>cyclops</artifactId>
 *      <version>10.4.1</version>
 *   </dependency>
 * 
 */
public interface Memoization {

    
    public static <T> Supplier<T> memoizeSupplier(final Supplier<T> s) {
        
        final Map<Long,T> lazy = new ConcurrentHashMap<>();
        return () -> lazy.computeIfAbsent(1L, i -> s.get());
    }
    
}
