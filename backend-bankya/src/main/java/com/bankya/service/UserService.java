package com.bankya.service;

import java.util.List;

import com.bankya.entity.UserEntity;

public interface UserService {

	public List<UserEntity> get() throws Exception;

	public UserEntity save(UserEntity users) throws Exception;

	public boolean delete(String username) throws Exception;

}
