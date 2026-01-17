package com.fintrack.account.dto;

import java.time.Instant;

import com.fintrack.account.accountEnum.AccountStatus;
import com.fintrack.account.accountEnum.AccountType;
import com.fintrack.account.accountEnum.KycStatus;
import com.fintrack.account.entity.Account;

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private Long userId;
    private AccountType accountType;
    private AccountStatus status;
    private KycStatus kycStatus;
    private String currency;
    private Instant createdAt;

    public AccountResponse(
            Long id,
            String accountNumber,
            Long userId,
            AccountType accountType,
            AccountStatus status,
            KycStatus kycStatus,
            String currency,
            Instant createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.accountType = accountType;
        this.status = status;
        this.kycStatus = kycStatus;
        this.currency = currency;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public Long getUserId() {
        return userId;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public AccountStatus getStatus() {
        return status;
    }
    public KycStatus getKycStatus() {
        return kycStatus;
    }
    public String getCurrency() {
        return currency;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            account.getUserId(),
            account.getAccountType(),
            account.getStatus(),
            account.getKycStatus(),
            account.getCurrency(),
            account.getCreatedAt()
        );
    }
}
