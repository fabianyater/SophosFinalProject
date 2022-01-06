package com.bankya.services;

import java.util.Optional;

import com.bankya.models.ProductModel;

public interface ProductService {
	public Iterable<ProductModel> findAll();

	public Optional<ProductModel> findById(int id);

	public ProductModel save(ProductModel client);

	public void deleteById(int id);
}
