package com.piggymetrics.notification.domain;

public record Item(String title, int amount, Currency currency, TimePeriod period, String icon) {}
