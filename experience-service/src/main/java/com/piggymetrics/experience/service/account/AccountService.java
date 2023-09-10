package com.piggymetrics.experience.service.account;

import com.piggymetrics.experience.domain.account.Account;
import java.util.List;

public interface AccountService {
    public List<Account> getAccounts();
    public String welcomeAccount();
    public String getToken();
}
