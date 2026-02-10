package com.fintrack.wallet.controller;

import java.util.List;

import com.fintrack.wallet.dto.CreateWalletRequest;
import com.fintrack.wallet.dto.WalletBalanceResponse;
import com.fintrack.wallet.dto.WalletResponse;
import com.fintrack.wallet.service.WalletService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/accounts/{accountId}/wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletController {
    @Inject
    private WalletService walletService;

    @GET
    @Path("/{currency}/balance")
    public WalletBalanceResponse getBalance(
            @PathParam("accountId") Long accountId,
            @PathParam("currency") String currency) {
        return walletService.getBalance(accountId, currency);
    }

    @GET
    public List<WalletResponse> getAllWallets(
            @PathParam("accountId") Long accountId) {
        return walletService.getWallets(accountId);
    }

    
    @POST
    public WalletResponse createWallet(
            @PathParam("accountId") Long accountId,
            CreateWalletRequest request) {
        return walletService.createWallet(accountId, request.currency);
    }

}
