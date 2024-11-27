package com.nicolaspiplard.katabank.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void withdraw_WithSufficientBalance_ShouldDecreaseBalance() {
        Account account = new Account(1L, BigDecimal.valueOf(100));
        account.withdraw(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    void withdraw_WithInsufficientBalance_ShouldThrowException() {
        Account account = new Account(1L, BigDecimal.valueOf(100));
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(BigDecimal.valueOf(150));
        });
    }
}
