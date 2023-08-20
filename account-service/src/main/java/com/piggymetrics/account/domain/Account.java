package com.piggymetrics.account.domain;

public record Account(String name, Date lastSeen, List<Item> incomes, List<Item> expenses, Saving saving, String note) {}
