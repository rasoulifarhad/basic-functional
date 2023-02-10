package com.farhad.example.functional.catbird.v2;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.fail;

import com.farhad.example.functional.catbird.v2.Cat;
import com.farhad.example.functional.catbird.v2.Bird;
import com.farhad.example.functional.catbird.v2.FullCat;
import com.farhad.example.functional.catbird.v2.CatWithCatch;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertAll;

public class CatBirdTest {

    @Test
    public void catBirdTest() {
    
        // BiFunction<Cat,Bird,FullCat> story = (c, b) -> c.capture(b)
        BiFunction<Cat,Bird,CatWithCatch> capture = (c, b) -> c.capture(b);
        // BiFunction<Cat,Bird,CatWithCatch> capture = Cat::capture;
        BiFunction<Cat,Bird,FullCat> story = capture.andThen(CatWithCatch::eat);
        FullCat fullCat = story.apply(new Cat(), new Bird());

        //
        BiFunction<Cat,Bird,FullCat> story2 =  ((BiFunction<Cat,Bird,CatWithCatch>) Cat::capture)
                                                                                        .andThen(CatWithCatch::eat);
        FullCat fullcat2 = story2.apply(new Cat(), new Bird());

        // 
        BiFunction<Cat,Bird,CatWithCatch> capture2 = (c, b) -> c.capture(b);
        Function<CatWithCatch,FullCat> eat = CatWithCatch::eat;
        BiFunction<Cat,Bird,FullCat> story3 = capture2.andThen(eat);
        story3.apply(new Cat(), new Bird());

    }
}
