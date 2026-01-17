package com.fintrack.account.dto;

import com.fintrack.account.accountEnum.AccountType;

public class CreateAccountRequest {
    private Long userId;
    private AccountType accountType;
    private String currency;

    public CreateAccountRequest() {
    }
    public CreateAccountRequest(Long userId, AccountType accountType, String currency) {
        this.userId = userId;
        this.accountType = accountType;
        this.currency = currency;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
}
