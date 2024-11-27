package com.nicolaspiplard.katabank.application.service;

import com.nicolaspiplard.katabank.application.port.output.AccountRepository;
import com.nicolaspiplard.katabank.domain.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    private AccountRepository repository;
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(AccountRepository.class);
        accountService = new AccountServiceImpl(repository);
    }

    @Test
    void withdraw_ShouldCallRepositoryAndUpdateAccount() {
        Account account = new Account(1L, BigDecimal.valueOf(100));
        when(repository.findById(1L)).thenReturn(Optional.of(account));

        accountService.withdraw(1L, BigDecimal.valueOf(50));

        verify(repository).save(account);
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    void withdraw_WithInsufficientBalance_ShouldThrowException() {
        Account account = new Account(1L, BigDecimal.valueOf(100));
        when(repository.findById(1L)).thenReturn(Optional.of(account));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.withdraw(1L, BigDecimal.valueOf(150));
        });

        assertEquals("Fonds insufisants", exception.getMessage());
        verify(repository, never()).save(any(Account.class));
    }
}