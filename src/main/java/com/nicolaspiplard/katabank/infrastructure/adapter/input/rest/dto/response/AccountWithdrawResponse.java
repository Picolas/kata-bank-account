package com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.response;

import java.math.BigDecimal;

public class AccountWithdrawResponse {
    private Long id;
    private BigDecimal balance;

    public AccountWithdrawResponse() {}

    public AccountWithdrawResponse(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
