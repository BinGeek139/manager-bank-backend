package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.Customer;
import com.ptit.managerbank.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(" FROM Customer cus WHERE upper(cus.name) like upper(?1)  ESCAPE  '!'")
    Page<Customer> findByFullName(String name, Pageable page);
    Optional<Customer> findFirstByCode(String code);

}
