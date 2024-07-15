package com.piggymetrics.experience.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Account(String name, Date lastSeen, List<Item> incomes, List<Item> expenses, Saving saving, String note) {}
