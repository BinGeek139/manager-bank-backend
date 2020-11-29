package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
    @Query(" FROM Staff st WHERE upper(st.fullName) like upper(?1)  ESCAPE  '!'")
    Page<Staff> findByFullName(String name, Pageable page);
}
