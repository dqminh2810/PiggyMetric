package com.piggymetrics.account.domain;

public record Saving(int amount, Currency currency, int interest, boolean deposit, boolean capitalization) {}
