package com.bankya.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.dao.OperationDao;
import com.bankya.models.OperationModel;
import com.bankya.models.ProductModel;

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
	public Optional<OperationModel> findById(Integer id) {
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
	public List<OperationModel> findProductOperations(Integer id, String type) {
		return operationDao.findProductOperations(id, type);
	}


}
