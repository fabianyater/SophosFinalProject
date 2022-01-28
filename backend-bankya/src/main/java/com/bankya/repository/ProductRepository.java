package com.bankya.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	@Query("SELECT op FROM product p INNER JOIN operation op ON p.product_id = op.product_id where p.product_id = :id and p.product_type = :type")
	List<OperationEntity> findProductOperations(@Param("id") Integer id, @Param("type") String type);
	
	@Query("SELECT p.product_state from product p WHERE p.product_id = :id")
	String findProductState(@Param("id") Integer id);
	
	@Query("SELECT p.product_type from product p WHERE p.product_id = :id")
	String findProductType(@Param("id") Integer id);
	
	@Query("SELECT p.product_id FROM product p WHERE p.product_number = :account_number")
	Integer findIdByAccountNumber(@Param("account_number") Integer account_number);
	
	@Query("select p.product_ammount from product p where p.product_id = :id")
	Double findBalance(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("UPDATE product p SET p.product_ammount = :value WHERE p.product_id = :id")
	void substractAmmount(@Param("id") Integer id, @Param("value") Double value);

	@Transactional
	@Modifying
	@Query("UPDATE product p SET p.product_ammount = :value WHERE p.product_id = :id")
	void addAmmount(@Param("id") Integer id, @Param("value") Double value);
}
