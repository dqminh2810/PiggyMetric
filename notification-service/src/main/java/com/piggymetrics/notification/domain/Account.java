package com.piggymetrics.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public record Account(String name, Date lastSeen, List<Item> incomes, List<Item> expenses, Saving saving, String note) {}
