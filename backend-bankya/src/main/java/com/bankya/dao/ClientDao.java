package com.bankya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankya.models.ClientModel;

public interface ClientDao extends JpaRepository<ClientModel, Integer> {
	@Query("SELECT p.product_state FROM client c INNER JOIN product p ON c.client_id = p.client_id WHERE c.client_id = :id")
	List<String> findClientProductsState(@Param("id") Integer id);
}
