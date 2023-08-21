package com.piggymetrics.account.domain;

public record Item(String title, int amount, Currency currency, TimePeriod period, String icon) {}
