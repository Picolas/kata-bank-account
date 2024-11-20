package com.nicolaspiplard.katabank.application.service;

import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.application.port.input.AccountService;
import com.nicolaspiplard.katabank.application.port.output.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    public final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        account.withdraw(amount);
        repository.save(account);
    }
}
