package com.bankya.services;

import java.util.Optional;

import com.bankya.models.ProductModel;

public interface ProductService {
	public Iterable<ProductModel> findAll();

	public Optional<ProductModel> findById(int id);

	public ProductModel save(ProductModel product);

	public void deleteById(int id);
	
	public Iterable<ProductModel> findClientProducts(Integer id);
	
	public Optional<ProductModel> updateProductState(Integer id, String value);
}
