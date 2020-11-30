package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.SavingAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SavingAccountRepository extends JpaRepository<SavingAccount,Integer> {
    @Query(" FROM SavingAccount acc WHERE upper(acc.code) like upper(?1)  ESCAPE  '!'")
    Page<SavingAccount> findByCode(String name, Pageable page);
}
