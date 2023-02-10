package com.farhad.example.functional.catbird.v2;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * v2
 */
@RequiredArgsConstructor
public class CatWithCatch {
    
    private final Bird catchedBird ;
    
    public FullCat  eat() {
        return new FullCat() ;
    }
}
