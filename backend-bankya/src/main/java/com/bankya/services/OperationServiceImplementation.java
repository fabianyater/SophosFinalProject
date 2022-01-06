package com.bankya.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.dao.OperationDao;
import com.bankya.models.OperationModel;

@Service
public class OperationServiceImplementation implements OperationService {
	@Autowired
	OperationDao operationDao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<OperationModel> findAll() {
		return operationDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<OperationModel> findById(int id) {
		return operationDao.findById(id);
	}

	@Override
	public OperationModel save(OperationModel operation) {
		return operationDao.save(operation);
	}

	@Override
	public void deleteById(int id) {
		operationDao.deleteById(id);
	}

}
