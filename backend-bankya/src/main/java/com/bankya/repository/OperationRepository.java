package com.bankya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankya.entity.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {
}
