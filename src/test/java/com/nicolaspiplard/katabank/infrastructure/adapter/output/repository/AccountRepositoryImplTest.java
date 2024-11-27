package com.nicolaspiplard.katabank.infrastructure.adapter.output.repository;

import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.repository.AccountRepositoryImpl;
import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.repository.JpaAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AccountRepositoryImplTest {

    @Autowired
    private JpaAccountRepository repository;

    @Test
    void saveAndFindAccount() {
        AccountRepositoryImpl adapter = new AccountRepositoryImpl(repository);

        Account account = new Account(null, BigDecimal.valueOf(100));
        account = adapter.save(account);

        assertNotNull(account.getId());

        Optional<Account> retrievedAccount = adapter.findById(account.getId());
        assertTrue(retrievedAccount.isPresent());
        assertEquals(BigDecimal.valueOf(100), retrievedAccount.get().getBalance());
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmptyOptional() {
        AccountRepositoryImpl adapter = new AccountRepositoryImpl(repository);

        Optional<Account> retrievedAccount = adapter.findById(999L);
        assertFalse(retrievedAccount.isPresent());
    }

};
