package com.nicolaspiplard.katabank.application.port.input;

import com.nicolaspiplard.katabank.domain.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account withdraw(Long accountId, BigDecimal amount);
}
