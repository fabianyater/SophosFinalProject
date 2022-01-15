package com.bankya.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.dao.ProductDao;
import com.bankya.models.ProductModel;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<ProductModel> findAll() {
		return productDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductModel> findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public ProductModel save(ProductModel product) {
		return productDao.save(product);
	}

	@Override
	public void deleteById(int id) {
		productDao.deleteById(id);
	}

	@Override
	public Iterable<ProductModel> findClientProducts(Integer id) {
		return productDao.findClientProducts(id);
	}

	@Override
	public Optional<ProductModel> updateProductState(Integer id, String value) {
		return productDao.updateProductState(id, value);
	}

}
