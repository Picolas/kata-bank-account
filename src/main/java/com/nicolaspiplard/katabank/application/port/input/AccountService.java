package com.nicolaspiplard.katabank.application.port.input;

import java.math.BigDecimal;

public interface AccountService {
    void withdraw(Long accountId, BigDecimal amount);
}
