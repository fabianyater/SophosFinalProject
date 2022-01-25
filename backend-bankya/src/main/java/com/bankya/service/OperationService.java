package com.bankya.service;

import java.util.List;
import java.util.Optional;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;

public interface OperationService {
	public Iterable<OperationEntity> findAll();

	public Optional<OperationEntity> findById(Integer id);

	public OperationEntity save(OperationEntity operation);

	public void deleteById(int id);
	
	public void substractAmmount(Integer id, Double value);
	
	public Double findBalance(Integer id);
	
	public void addAmmount(Integer id, Double value);
	
	public Integer findIdByAccountNumber(Integer id);
	
	public String findProductType(Integer id);
	
	public String findProductState(Integer id);
	
	public List<OperationEntity> findProductOperations(Integer id, String type);
	
}
