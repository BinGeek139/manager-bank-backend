package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountBankRepository  extends JpaRepository<AccountBank,Integer> {
    @Query(" FROM AccountBank acc WHERE upper(acc.code) like upper(?1)  ESCAPE  '!'")
    Page<AccountBank> findByCode(String name, Pageable page);
}
