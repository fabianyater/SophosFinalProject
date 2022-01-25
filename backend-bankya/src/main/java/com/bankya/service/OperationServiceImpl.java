package com.bankya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;
import com.bankya.repository.OperationRepository;

@Service
public class OperationServiceImpl implements OperationService {
	@Autowired
	OperationRepository operationDao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<OperationEntity> findAll() {
		return operationDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<OperationEntity> findById(Integer id) {
		return operationDao.findById(id);
	}

	@Override
	public OperationEntity save(OperationEntity operation) {
		return operationDao.save(operation);
	}

	@Override
	public void deleteById(int id) {
		operationDao.deleteById(id);
	}

	@Override
	public void substractAmmount(Integer id, Double value) {
		operationDao.substractAmmount(id, value);
	}

	@Override
	public Double findBalance(Integer id) {
		return operationDao.findBalance(id);
	}

	@Override
	public void addAmmount(Integer id, Double value) {
		operationDao.addAmmount(id, value);
	}

	@Override
	public Integer findIdByAccountNumber(Integer id) {
		return operationDao.findIdByAccountNumber(id);
	}

	@Override
	public String findProductType(Integer id) {
		return operationDao.findProductType(id);
	}

	@Override
	public String findProductState(Integer id) {
		return operationDao.findProductState(id);
	}

	@Override
	public List<OperationEntity> findProductOperations(Integer id, String type) {
		return operationDao.findProductOperations(id, type);
	}


}
