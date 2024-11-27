package com.nicolaspiplard.katabank.application.port.output;

import com.nicolaspiplard.katabank.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long id);
    Account save(Account account);
}
