package com.bankya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankya.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	UserEntity findByUsername(String username);
	
}
