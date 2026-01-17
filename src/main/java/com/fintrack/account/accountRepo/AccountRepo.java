package com.fintrack.account.accountRepo;

import com.fintrack.account.entity.Account;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AccountRepo implements PanacheRepository<Account> {

}
