package com.piggymetrics.experience.domain.account;

public enum Currency {
    USD, EUR, RUB;
    
    public static Currency getDefault(){return USD;}
}
