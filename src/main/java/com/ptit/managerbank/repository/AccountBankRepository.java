package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountBankRepository extends JpaRepository<AccountBank,Integer> {
    @Query(" FROM AccountBank acc WHERE upper(acc.code)   like upper(?1) ESCAPE  '!' AND acc.type = ?2"  )
    Page<AccountBank> findByCodeAndType(String name,String type, Pageable page);
    Set<AccountBank> findByCustomerAndType(Customer customer,String type );
    Optional<AccountBank> findFirstByCode(String code);
    @Query("FROM AccountBank acc WHERE acc.createdBy= ?1 AND acc.type= ?2 AND acc.createdDate >= ?3 and  acc <= ?4 ")
    List<AccountBank> findAccountCreatedby(String username, String type, Date start, Date end);
}