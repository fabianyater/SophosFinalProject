package com.bankya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	@Query("UPDATE product p SET p.product_state = :value WHERE p.product_id = :id")
	Optional<ProductEntity> cancelProduct(@Param("id") Integer id, @Param("value") String value);

	@Query("SELECT p FROM customer c INNER JOIN product p ON c.customer_id = p.client_id WHERE c.customer_id = :id and p.product_state <> 'C'")
	Iterable<ProductEntity> findClientProducts(@Param("id") Integer id);
}
