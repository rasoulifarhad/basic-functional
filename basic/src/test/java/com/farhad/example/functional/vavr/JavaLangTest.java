package com.farhad.example.functional.vavr;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function5;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
// import static org.hamcrest.MatcherAssert.assertThat; 
// import static org.hamcrest.Matchers.*;

/**
 * Vavr (formerly called Javaslang) is a functional library for Java 8+ that provides persistent data types and functional control structures.
 */

@Slf4j
public class JavaLangTest {
    

    
    @Test
    public void vavrStreamTest() {
        Stream.of(1,2,3)
               .map(Object::toString)
               .out(new PrintStream(System.out));

        log.info("{}",join("Java", "Advent"));

        log.info("{}",List.of("Java", "Advent").mkString(", "));


    }

    String join(String... words) {
        return 
            List.of(words)
                .intersperse(", ")
                .foldLeft(new  StringBuilder(), StringBuilder::append)
                .toString();
    }

    @Test
    public void vavrTuple2Test() {

        Tuple2<String,Integer>  entry = Tuple.of("A", 1);
        log.info("_1: {}  _2: {}", entry._1,entry._2);


        Tuple2<String,Integer> java8 = Tuple.of("Java", 8);
        // (Jav, 1)
        Tuple2<String,Integer> that = java8.map(
                                      s -> s.substring(0,3) ,
                                      i -> i/8 );                   
        log.info("that: {}" , that);

        Tuple2<String,Integer> that2 = java8.map(
                                      (s,i) -> Tuple.of(s.substring(0,3),i/8) );                   
        log.info("that2: {}" , that2);


        String thatString = java8.apply(
                                     (s,i) -> s.substring(0,3) + i/8);
        log.info("thatString: {}" , thatString);

    }

    @Test
    public void vavr_GroupBy_and_zipWithIndex_Test() {

        //  LinkedHashMap( (false, List(1, 3) ) , (true, List(2) ) )
        Map<Boolean, List<Integer>> groupedByOddEven = List.of(1,2,3).groupBy(i -> i%2 == 0) ;
        log.info("{}", groupedByOddEven);

        //  List( (a, 0) , (b, 1) , (c, 2) )
        List<Tuple2<Character, Integer>> zipped = List.of('a','b','c').zipWithIndex();
        log.info("{}", zipped);

    }

    @Test
    public void function_Test() {

        Function2<Integer,Integer,Integer> sum = (a,b) -> a + b ;
        log.info("sum1: {}" , sum.apply(10, 20));
 
        Function2<Integer,Integer,Integer> sum2 = new Function2<Integer,Integer,Integer>() {

            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
            
        };
        log.info("sum2: {}" , sum2.apply(10, 20));

        Function2<Integer,Integer,Integer> sum3 = Function2.of(this::sum3);
        log.info("sum3: {}" , sum3.apply(10, 20));

    }

    private Integer sum3(Integer a,Integer b ) {
        return a + b ;
    }

    @Test
    public void function_composition_Test() {

        Function1<Integer,Integer> plusOne = i -> i+1 ;
        Function1<Integer,Integer> multiplyByTwo = i -> i*2;

        Function1<Integer,Integer> add1AndMultiplyBy2WithAndThen = plusOne.andThen(multiplyByTwo);
        log.info("Given {}  When add1AndMultiplyBy2WithAndThen  Then {}",2,add1AndMultiplyBy2WithAndThen.apply(2));

        Function1<Integer,Integer> composeMultiplyBy2AndAdd1 =multiplyByTwo.compose(plusOne);
        log.info("Given {}  When composeMultiplyBy2AndAdd1  Then {}",2,composeMultiplyBy2AndAdd1.apply(2));

        Function1<Integer,Integer> composeAdd1AndMultiplyBy2 = plusOne.compose(multiplyByTwo);
        log.info("Given {}  When composeAdd1AndMultiplyBy2  Then {}",2,composeAdd1AndMultiplyBy2.apply(2));


    }

    @Test
    public void function_lifting_Test() {

        // The following method divide is a partial function that only accepts non-zero divisors.
        Function2<Integer,Integer,Integer> divide = (a,b) -> a/b ;
        // divide.apply(10/0);
 
        // We use lift to turn divide into a total function that is defined for all inputs.
        Function2<Integer,Integer,Option<Integer>> safeDivide = Function2.lift(divide);
        Option<Integer> i1 = safeDivide.apply(1, 0);
        Option<Integer> i2 = safeDivide.apply(4, 2);
        log.info("{} -- {}", i1,i2);


    }

    @Test
    public void another_function_lifting_Test() {

    
 
        // We may lift the sum method by providing the methods reference.
        Function2<Integer,Integer,Option<Integer>> sum = Function2.lift(this::partialSum);
        Option<Integer>  optionalResult = sum.apply(-1, 12);
        Option<Integer>  anotherOptionalResult = sum.apply(1, 12);
        log.info("{} -- {}", optionalResult,anotherOptionalResult);

  
    }

    //The following method partialSum is a partial function that only accepts positive input values.
    private Integer partialSum(int first,int second) {
        if(first < 0 || second < 0){
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second ;

    }

    // Partial application allows you to derive a new function from an existing one by fixing some values.
    // You can fix one or more parameters, and the number of fixed parameters defines the arity of the new function such that 
    // new arity = (original arity - fixed parameters). 
    // The parameters are bound from left to right.
    @Test
    public void partial_function_test() {

        Function2<Integer,Integer,Integer>  sum = (a,b) -> a + b ;
        //The first parameter a is fixed to the value 2.
        Function1<Integer,Integer> add2 = sum.apply(2) ;
        Integer result = add2.apply(4);
        log.info("{}", result);
        assertThat(result).isEqualTo(6);
        
        
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sumWith5Param = (a,b,c,d,e) -> a + b + c + d + e ;
        Function2<Integer, Integer, Integer> add6 = sumWith5Param.apply(2, 3, 1);
        Integer result2 = add6.apply(4, 3);
        log.info("{}", result2);
        assertThat(result2).isEqualTo(13);

    }

    // Currying is a technique to partially apply a function by fixing a value for one of the parameters, resulting in a 
    // Function1 function that returns a Function1.
    // When a Function2 is curried, the result is indistinguishable from the partial application of a Function2 because 
    // both result in a 1-arity function.
    @Test
    public void currying_test() {

        Function2<Integer, Integer, Integer> sum = (a , b) -> a + b ;
        Function1<Integer,Integer> add2 = sum.curried().apply(2);
        Integer result = add2.apply(4);
        assertThat(result).isEqualTo(6);
        

        Function3<Integer, Integer, Integer, Integer> anotherSum = (a, b, c) -> a + b + c;
        final Function1<Integer,Function1<Integer,Integer>>  anatherAdd2 =  anotherSum.curried().apply(2);
        Integer anotherResult = anatherAdd2.apply(4).apply(3);
        assertThat(anotherResult).isEqualTo(9);
    }

    // Memoization is a form of caching. A memoized function executes only once and then returns the result from a cache.
    @Test
    public void memoization_test() {
        
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        assertThat(randomValue1).isEqualTo(randomValue2);
    }

    // Try is a monadic container type which represents a computation that may either result in an exception, or return a 
    // successfully computed value. 
    // Instances of Try, are either an instance of Success or Failure.
    @Test
    public void try_test() {

    }

    //Lazy is a monadic container type which represents a lazy evaluated value. Compared to a Supplier, Lazy is memoizing, i.e. it 
    // evaluates only once and therefore is referentially transparent.
    @Test
    public void lazy_test() {

        Lazy<Double> lazy = Lazy.of(Math::random);
        log.info("{}",lazy.isEvaluated());
        double val1 = lazy.get();
        log.info("{}",lazy.isEvaluated());
        double val2 = lazy.get();

        assertThat(val1).isEqualTo(val2);

    }
}


