package com.farhad.example.functional.catbird.v2;

import lombok.NoArgsConstructor;

/**
 * v2
 */
@NoArgsConstructor
public class Cat {

    public CatWithCatch capture(Bird bird) {
        return new CatWithCatch(bird);
    }
    
}
