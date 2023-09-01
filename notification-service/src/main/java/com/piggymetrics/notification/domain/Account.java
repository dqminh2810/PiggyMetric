package com.piggymetrics.notification.domain;

import java.util.Date;
import java.util.List;

public record Account(String name, Date lastSeen, List<Item> incomes, List<Item> expenses, Saving saving, String note) {}
