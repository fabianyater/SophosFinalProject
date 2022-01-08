package com.bankya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.models.ProductModel;

public interface ProductDao extends JpaRepository<ProductModel, Integer> {
	@Query("UPDATE product p SET p.product_state = 'C' WHERE p.product_id = :id")
	Optional<ProductModel> cancelProduct(@Param("id") Integer id);

	@Query("SELECT p FROM client c INNER JOIN product p ON c.client_id = p.client_id WHERE c.client_id = :id")
	Iterable<ProductModel> findClientProducts(@Param("id") Integer id);
}
