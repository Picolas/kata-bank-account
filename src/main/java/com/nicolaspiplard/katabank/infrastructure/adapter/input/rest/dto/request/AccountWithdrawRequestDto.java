package com.nicolaspiplard.katabank.infrastructure.adapter.input.rest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountWithdrawRequestDto {

    @NotNull(message = "Le montant ne doit pas être nul")
    @Min(value = 1, message = "Le montant doit être supérieur à zéro")
    private BigDecimal amount;

    public AccountWithdrawRequestDto(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
