package com.bankya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	@Query("SELECT p.product_state FROM customer c INNER JOIN product p ON c.customer_id = p.client_id WHERE c.customer_id = :id")
	List<String> findCustomerProductsStateById(@Param("id") Integer id);
}
