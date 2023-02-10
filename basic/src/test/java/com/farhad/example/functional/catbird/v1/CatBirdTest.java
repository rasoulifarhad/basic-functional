package com.farhad.example.functional.catbird.v1;

import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.farhad.example.functional.catbird.v1.Bird;
import com.farhad.example.functional.catbird.v1.Cat;

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
import static org.junit.jupiter.api.Assertions.assertAll;


public class CatBirdTest {
    

    @Test
    public void catEatTheBird() {

        Cat cat  = new Cat();
        Bird bird = new Bird() ;

        cat.capture(bird);
        cat.eat(); ;
       
        assertThat(cat.getBird()).isNull();
        assertThat(cat.isFull()).isTrue();


    }
}
