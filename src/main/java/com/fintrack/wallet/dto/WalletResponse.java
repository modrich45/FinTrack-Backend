package com.fintrack.wallet.dto;

import java.math.BigDecimal;

import com.fintrack.wallet.entity.Wallet;

public class WalletResponse {
    public Long walletId;
    private String currency;
    private BigDecimal availableBalance;
    private BigDecimal lockedBalance;

    public WalletResponse() {
    }

    public WalletResponse(String currency, BigDecimal availableBalance, BigDecimal lockedBalance) {
        this.currency = currency;
        this.availableBalance = availableBalance;
        this.lockedBalance = lockedBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public BigDecimal getLockedBalance() {
        return lockedBalance;
    }

    public static WalletResponse from(Wallet wallet) {
        WalletResponse r = new WalletResponse();
        r.walletId = wallet.getId();
        r.currency = wallet.getCurrency();
        r.availableBalance = wallet.getAvailableBalance();
        r.lockedBalance = wallet.getLockedBalance();
        return r;
    }
}
