package com.piggymetrics.experience.domain.account;
public record Saving(int amount, Currency currency, int interest, boolean deposit, boolean capitalization) {}
