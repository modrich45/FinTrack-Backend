package com.fintrack.account.accountService;

import java.util.List;
import java.util.stream.Collectors;

import com.fintrack.account.accountRepo.AccountRepo;
import com.fintrack.account.dto.AccountResponse;
import com.fintrack.account.dto.CreateAccountRequest;
import com.fintrack.account.entity.Account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {

    @Inject
    private AccountRepo accountRepo;

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {

        Account account = Account.create(generateAccountNo(),request.getUserId(),request.getAccountType(),request.getCurrency());
        accountRepo.persist(account);
        return AccountResponse.fromAccount(account);
    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepo.findById(id);
        if (account == null) {
            throw new IllegalArgumentException("Account not found with ID: " + id);
        }
        
        return AccountResponse.fromAccount(account);
    }

    public List<AccountResponse> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepo.list("userId", userId);
        return accounts.stream()
                       .map(AccountResponse::fromAccount)
                       .collect(Collectors.toList());
    }

    private String generateAccountNo() {
        // Simple account number generation logic (for demonstration purposes)
        return "ACCT-" + System.currentTimeMillis();
    }

    
}
