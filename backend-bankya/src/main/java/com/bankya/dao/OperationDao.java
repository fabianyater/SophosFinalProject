package com.bankya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankya.models.OperationModel;

public interface OperationDao extends JpaRepository<OperationModel, Integer>{

}
