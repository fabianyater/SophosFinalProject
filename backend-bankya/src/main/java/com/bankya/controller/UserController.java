package com.bankya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankya.entity.UserEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.service.UserService;
import com.bankya.util.JwtUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/auth")
	public ResponseEntity<GeneralResponse<UserEntity>> login(@RequestBody UserEntity user) {
		GeneralResponse<UserEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		String messageResult = "";
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			user.setJwt(jwtUtils.generateToken(user.getUsername()));
			user.setPassword(null);
			messageResult = "Login successfull for user: " + user.getUsername() + ".";
			
			response.setToken(user.getJwt());
			response.setMessageResult(messageResult);
			response.setMessage("Operation successfull");
			response.setErrorCode(1);
			response.setSuccess(true);
			response.setData(user);
			status = HttpStatus.CREATED;

		} catch (AuthenticationException authex) {
			String message = "Incorrect user or password.";
			status = HttpStatus.FORBIDDEN;
			response.setMessage(message);
			response.setSuccess(false);
			response.setErrorCode(0);
		} catch (Exception e) {
			String message = "Something went wrong. Please contact support.";
			status = HttpStatus.FORBIDDEN;
			response.setMessage(message);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}

	@GetMapping
	public ResponseEntity<GeneralResponse<List<UserEntity>>> getUser() {

		GeneralResponse<List<UserEntity>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<UserEntity> data = null;

		try {

			data = userService.get();
			String msg = "It found " + data.size() + " users.";

			response.setMessage(msg);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {

			String msg = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}

	@PostMapping("/create")
	public ResponseEntity<GeneralResponse<UserEntity>> save(@RequestBody UserEntity user) {

		GeneralResponse<UserEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		UserEntity data = null;

		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			data = userService.save(user);
			String msg = "It Save " + data + " users.";

			response.setMessage(msg);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {

			String msg = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(msg);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}

}
