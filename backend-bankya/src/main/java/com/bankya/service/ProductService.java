package com.bankya.service;

import java.util.List;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;

public interface ProductService {

	public ProductEntity findById(int id) throws Exception;

	public ProductEntity save(ProductEntity product) throws Exception;
	
	public List<OperationEntity> findProductOperations(Integer id, String type) throws Exception;
	
	public String findProductState(Integer id) throws Exception;
	
	public String findProductType(Integer id) throws Exception;
	
	public Integer findIdByAccountNumber(Integer id) throws Exception;
	
	public Double findBalance(Integer id) throws Exception;
	
	public void addAmmount(Integer id, Double value) throws Exception;
	
	public void substractAmmount(Integer id, Double value) throws Exception;
}
