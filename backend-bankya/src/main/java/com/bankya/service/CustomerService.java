package com.bankya.service;

import java.util.List;

import com.bankya.entity.CustomerEntity;

public interface CustomerService {

	public List<CustomerEntity> findAll() throws Exception;

	public CustomerEntity findById(int id) throws Exception;

	public CustomerEntity save(CustomerEntity client) throws Exception;

	public void deleteById(int id) throws Exception;
	
	public List<String> findCustomerProductsStateById(Integer id) throws Exception;
	
}
