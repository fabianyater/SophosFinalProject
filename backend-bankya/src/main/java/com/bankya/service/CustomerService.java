package com.bankya.service;

import java.util.List;

import com.bankya.entity.CustomerEntity;
import com.bankya.entity.ProductEntity;

public interface CustomerService {

	public List<CustomerEntity> findAll() throws Exception;

	public CustomerEntity findById(int id) throws Exception;

	public CustomerEntity save(CustomerEntity client) throws Exception;

	public void deleteById(int id) throws Exception;
	
	public List<String> findCustomerProductsStateById(Integer id) throws Exception;
	
	public List<ProductEntity> findCustomerProducts(Integer id) throws Exception;
	
	public ProductEntity findCustomerProductById(Integer customerId, Integer productId) throws Exception;
	
}
