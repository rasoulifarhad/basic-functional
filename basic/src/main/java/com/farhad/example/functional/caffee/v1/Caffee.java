package com.farhad.example.functional.caffee.v1;

import lombok.Getter;

@Getter
public class Caffee {
    
    private static final String DEFAULT_CAFFEE = "DEFAULT_CAFFEE";
    private static final int DEFAULT_CAFFEE_PRICE = 10 ;
    
    private String caffeName ;

    private int caffeePrice;

    public Caffee(String caffeeName,int caffeePrice) {
    
        this.caffeName = caffeeName;
        this.caffeePrice = caffeePrice;
    }

    public Caffee() {
    
        this.caffeName = Caffee.DEFAULT_CAFFEE;
        this.caffeePrice = Caffee.DEFAULT_CAFFEE_PRICE;
    }


}
