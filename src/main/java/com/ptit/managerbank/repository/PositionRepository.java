package com.ptit.managerbank.repository;

import com.ptit.managerbank.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
