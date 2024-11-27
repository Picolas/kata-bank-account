package com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.repository;

import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.entity.AccountEntity;
import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.application.port.output.AccountRepository;
import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.mapper.AccountMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JpaAccountRepository repository;

    public AccountRepositoryImpl(JpaAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id)
                .map(AccountMapper::toDomain);
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);
        AccountEntity savedEntity = repository.save(entity);
        return AccountMapper.toDomain(savedEntity);
    }
}
