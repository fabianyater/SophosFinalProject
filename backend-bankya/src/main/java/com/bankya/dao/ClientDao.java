package com.bankya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankya.models.ClientModel;

public interface ClientDao extends JpaRepository<ClientModel, Integer> {

}
