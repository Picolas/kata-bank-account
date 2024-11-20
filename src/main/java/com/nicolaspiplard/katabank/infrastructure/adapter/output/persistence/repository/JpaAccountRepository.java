package com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.repository;

import com.nicolaspiplard.katabank.infrastructure.adapter.output.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
}
