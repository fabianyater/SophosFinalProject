package com.bankya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;
import com.bankya.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ProductEntity save(ProductEntity product) throws Exception {
		return productRepository.save(product);
	}

	@Override
	public ProductEntity findById(int id) throws Exception {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<OperationEntity> findProductOperations(Integer id, String type) throws Exception {
		return productRepository.findProductOperations(id, type);
	}

	@Override
	public String findProductState(Integer id) throws Exception {
		return productRepository.findProductState(id);
	}

	@Override
	public String findProductType(Integer id) throws Exception {
		return productRepository.findProductType(id);
	}

	@Override
	public Integer findIdByAccountNumber(Integer id) throws Exception {
		return productRepository.findIdByAccountNumber(id);
	}

	@Override
	public Double findBalance(Integer id) throws Exception {
		return productRepository.findBalance(id);
	}

	@Override
	public void addAmmount(Integer id, Double value) throws Exception {
		productRepository.addAmmount(id, value);
	}

	@Override
	public void substractAmmount(Integer id, Double value) throws Exception {
		productRepository.substractAmmount(id, value);
		
	}

}
