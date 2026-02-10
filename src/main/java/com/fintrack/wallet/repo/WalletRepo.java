package com.fintrack.wallet.repo;

import com.fintrack.wallet.dto.WalletBalanceResponse;
import com.fintrack.wallet.entity.Wallet;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;

@ApplicationScoped
public class WalletRepo implements PanacheRepository<Wallet>{

    public WalletBalanceResponse getBalance(
        @PathParam("accountId") Long accountId,
        @PathParam("currency") String currency
    ) {
        Wallet wallet = find("accountId = ?1 and currency = ?2", accountId, currency).firstResult();
        if (wallet == null) {
            throw new RuntimeException("Wallet not found for accountId: " + accountId + " and currency: " + currency);
        }
        return new WalletBalanceResponse(
            currency,
            wallet.getAvailableBalance()+wallet.getLockedBalance().toString()
        );
    }

    public java.util.Optional<Wallet> findByAccountAndCurrency(Long accountId, String currency) {
        return find("accountId = ?1 and currency = ?2", accountId, currency).firstResultOptional();
    }
}
