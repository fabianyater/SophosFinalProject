package com.bankya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.entity.CustomerEntity;
import com.bankya.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<CustomerEntity> findAll() throws Exception {
		return customerRepository.findAll();
	}

	
	@Override
	public CustomerEntity findById(int id) throws Exception {
		return customerRepository.findById(id).get();
	}

	@Override
	@Transactional
	public CustomerEntity save(CustomerEntity client) throws Exception {
		return customerRepository.save(client);
	}

	@Override
	public void deleteById(int id) throws Exception {
		customerRepository.deleteById(id);
	}

	@Override
	public List<String> findCustomerProductsStateById(Integer id) throws Exception {
		return customerRepository.findCustomerProductsStateById(id);
	}

}
