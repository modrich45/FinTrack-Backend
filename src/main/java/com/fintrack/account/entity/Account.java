package com.fintrack.account.entity;

import java.time.Instant;

import com.fintrack.account.accountEnum.AccountStatus;
import com.fintrack.account.accountEnum.AccountType;
import com.fintrack.account.accountEnum.KycStatus;

import io.quarkus.Generated;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts", indexes = {
        @Index(name = "idx_account_user", columnList = "user_id"),
        @Index(name = "idx_account_status", columnList = "status")
})
public class Account extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", nullable = false, unique = true, updatable = false)
    private String accountNumber;
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status", nullable = false)
    private KycStatus kycStatus;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

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

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    protected Account() {
    }

    public static Account create(
            String accountNumber,
            Long userId,
            AccountType type,
            String currency) {
        Account acc = new Account();
        acc.accountNumber = accountNumber;
        acc.userId = userId;
        acc.accountType = type;
        acc.currency = currency;
        acc.status = AccountStatus.ACTIVE;
        acc.kycStatus = KycStatus.NOT_STARTED;
        acc.createdAt = Instant.now();
        acc.updatedAt = acc.createdAt;
        return acc;
    }

    public void freeze() {
        if (status == AccountStatus.CLOSED) {
            throw new IllegalStateException("Closed account cannot be frozen");
        }
        this.status = AccountStatus.FROZEN;
        touch();
    }

    public void close() {
        if (status == AccountStatus.CLOSED) {
            return;
        }
        this.status = AccountStatus.CLOSED;
        touch();
    }

    public void verifyKyc() {
        if (kycStatus != KycStatus.PENDING) {
            throw new IllegalStateException("Invalid KYC transition");
        }
        this.kycStatus = KycStatus.VERIFIED;
        touch();
    }

    public void suspend() {
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Only active accounts can be suspended");
        }
        this.status = AccountStatus.SUSPENDED;
        touch();
    }

    public void activate() {
        if (status != AccountStatus.SUSPENDED) {
            throw new IllegalStateException("Only suspended accounts can be activated");
        }
        this.status = AccountStatus.ACTIVE;
        touch();
    }

    public void submitKyc() {
        if (kycStatus != KycStatus.NOT_STARTED) {
            throw new IllegalStateException("KYC already submitted");
        }
        this.kycStatus = KycStatus.PENDING;
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }
}
