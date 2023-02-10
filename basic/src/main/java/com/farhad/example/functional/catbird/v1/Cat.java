package com.farhad.example.functional.catbird.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * v1
 */
@NoArgsConstructor
@Getter
public class Cat {
    
    private Bird bird ;
    private boolean full;

    public void capture(Bird bird) {
        this.bird = bird;
    }

    public void eat() {
        full = true;
        bird = null;
    }
}
