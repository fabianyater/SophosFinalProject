package com.bankya.services;

import java.util.List;
import java.util.Optional;

import com.bankya.models.OperationModel;
import com.bankya.models.ProductModel;

public interface OperationService {
	public Iterable<OperationModel> findAll();

	public Optional<OperationModel> findById(Integer id);

	public OperationModel save(OperationModel operation);

	public void deleteById(int id);
	
	public void substractAmmount(Integer id, Double value);
	
	public Double findBalance(Integer id);
	
	public void addAmmount(Integer id, Double value);
	
	public Integer findIdByAccountNumber(Integer id);
	
	public String findProductType(Integer id);
	
	public String findProductState(Integer id);
	
	public List<OperationModel> findProductOperations(Integer id, String type);
	
}
