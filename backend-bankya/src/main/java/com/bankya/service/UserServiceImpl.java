package com.bankya.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankya.entity.UserEntity;
import com.bankya.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserEntity> get() throws Exception {
		return userRepository.findAll();
	}

	@Override
	public UserEntity save(UserEntity user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public boolean delete(String username) throws Exception {
		userRepository.deleteById(username);
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity usr = userRepository.findByUsername(username);
		UserDetails userDetails = new User(usr.getUsername(), usr.getPassword(), new ArrayList<>());
		return userDetails;
	}
}
