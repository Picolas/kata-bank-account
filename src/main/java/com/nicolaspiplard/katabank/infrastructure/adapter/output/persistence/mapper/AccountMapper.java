package com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.mapper;

import com.nicolaspiplard.katabank.domain.model.Account;
import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.entity.AccountEntity;

public class AccountMapper {

    public static Account toDomain(AccountEntity accountEntity) {
        return new Account(accountEntity.getId(), accountEntity.getBalance());
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountEntity(account.getId(), account.getBalance());
    }

}
