package com.bankya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.entity.CustomerEntity;
import com.bankya.entity.ProductEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	@Query("SELECT p.product_state FROM customer c INNER JOIN product p ON c.customer_id = p.customer_id WHERE c.customer_id = :id")
	List<String> findCustomerProductsStateById(@Param("id") Integer id);
	
	@Query("SELECT p FROM customer c INNER JOIN product p ON c.customer_id = p.customer_id WHERE c.customer_id = :id and p.product_state <> 'C'")
	List<ProductEntity> findCuctomerProducts(@Param("id") Integer id);
	
	@Query("select p from customer c inner join product p ON c.customer_id = p.customer_id  where p.product_id = :productId and c.customer_id = :customerId")
	ProductEntity findCustomerProductById(@Param("customerId") Integer customerId, @Param("productId") Integer productId);
}
