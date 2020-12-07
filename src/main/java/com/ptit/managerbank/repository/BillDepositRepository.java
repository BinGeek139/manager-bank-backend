package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.AccountBank;
import com.ptit.managerbank.model.BillDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillDepositRepository extends JpaRepository<BillDeposit,Integer> {
    @Query("from BillDeposit bill where bill.accountBankSend.id= ?1 ORDER BY  bill.createdBy")
    List<BillDeposit> findByAccountBankSend(Integer id);
}
