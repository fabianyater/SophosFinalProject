package com.bankya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankya.models.ProductModel;

public interface ProductDao extends JpaRepository<ProductModel, Integer> {

}
