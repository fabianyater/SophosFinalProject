package com.bankya.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.entity.ProductEntity;
import com.bankya.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productDao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<ProductEntity> findAll() {
		return productDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductEntity> findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public ProductEntity save(ProductEntity product) {
		return productDao.save(product);
	}

	@Override
	public void deleteById(int id) {
		productDao.deleteById(id);
	}

	@Override
	public Iterable<ProductEntity> findClientProducts(Integer id) {
		return productDao.findClientProducts(id);
	}


}
