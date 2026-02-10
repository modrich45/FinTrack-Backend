package com.fintrack.wallet.dto;

public class WalletBalanceResponse {
    private String currency;
    private String availableBalance;

    public WalletBalanceResponse(String currency, String availableBalance) {
        this.currency = currency;
        this.availableBalance = availableBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }
}
