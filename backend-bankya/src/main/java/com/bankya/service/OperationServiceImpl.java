package com.bankya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankya.entity.OperationEntity;
import com.bankya.repository.OperationRepository;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	OperationRepository operationRepository;

	@Override
	public OperationEntity save(OperationEntity operation) {
		return operationRepository.save(operation);
	}
}
