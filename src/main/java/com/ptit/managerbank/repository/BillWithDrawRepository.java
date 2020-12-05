package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.BillWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillWithDrawRepository extends JpaRepository<BillWithdraw,Integer> {
}
