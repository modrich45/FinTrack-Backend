package com.fintrack.wallet.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.fintrack.wallet.dto.WalletBalanceResponse;
import com.fintrack.wallet.dto.WalletResponse;
import com.fintrack.wallet.entity.Wallet;
import com.fintrack.wallet.repo.WalletRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class WalletService {
    @Inject
    private WalletRepo walletRepo;

    public WalletBalanceResponse getBalance(Long accountId, String currency) {
        return walletRepo.getBalance(accountId, currency);
    }

    public List<WalletResponse> getWallets(Long accountId) {
        return walletRepo.list("accountId", accountId)
                .stream()
                .map(wallet -> new WalletResponse(
                        wallet.getCurrency(),
                        wallet.getAvailableBalance(),
                        wallet.getLockedBalance()))
                .collect(Collectors.toList());
    }

    @Transactional
    public WalletResponse createWallet(Long accountId, String currency) {

        
        walletRepo.findByAccountAndCurrency(accountId, currency)
                .ifPresent(w -> {
                    throw new WebApplicationException(
                            "Wallet already exists", 409);
                });

        
        Wallet wallet = new Wallet();
        wallet.setAccountId(accountId);
        wallet.setCurrency(currency);
        wallet.setAvailableBalance(BigDecimal.ZERO);
        wallet.setLockedBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(Instant.now());
        wallet.setUpdatedAt(Instant.now());

        walletRepo.persist(wallet);

        return WalletResponse.from(wallet);
    }
}
