package com.bankya.service;

import java.util.Optional;

import com.bankya.entity.ProductEntity;

public interface ProductService {
	public Iterable<ProductEntity> findAll();

	public Optional<ProductEntity> findById(int id);

	public ProductEntity save(ProductEntity product);

	public void deleteById(int id);
	
	public Iterable<ProductEntity> findClientProducts(Integer id);
}
