package com.piggymetrics.experience.domain.account;
public record Item(String title, int amount, Currency currency, TimePeriod period, String icon) {}
