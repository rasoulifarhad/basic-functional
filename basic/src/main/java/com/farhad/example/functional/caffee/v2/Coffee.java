package com.farhad.example.functional.caffee.v2;

import lombok.Getter;

@Getter
public class Coffee {
    
    private static final String DEFAULT_CAFFEE = "DEFAULT_CAFFEE";
    private static final int DEFAULT_CAFFEE_PRICE = 10 ;
    
    private String caffeName ;

    private int caffeePrice;

    public Coffee(String caffeeName,int caffeePrice) {
    
        this.caffeName = caffeeName;
        this.caffeePrice = caffeePrice;
    }

    public Coffee() {
    
        this.caffeName = Coffee.DEFAULT_CAFFEE;
        this.caffeePrice = Coffee.DEFAULT_CAFFEE_PRICE;
    }


}