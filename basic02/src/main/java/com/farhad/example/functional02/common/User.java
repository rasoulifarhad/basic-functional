package com.farhad.example.functional02.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class User {
 
    private final long userId;
    private final String name;
}
