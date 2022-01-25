package com.bankya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankya.entity.CustomerEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<GeneralResponse<CustomerEntity>> addCustomer(@RequestBody CustomerEntity customer) {
		GeneralResponse<CustomerEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		CustomerEntity data = null;
		String message = "";

		try {

			data = customerService.save(customer);
			message = "Customer succesfully created";

			response.setMessage(message);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.CREATED;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, status);
	}

	@GetMapping
	public ResponseEntity<GeneralResponse<List<CustomerEntity>>> getCustomers() {

		GeneralResponse<List<CustomerEntity>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<CustomerEntity> data = null;
		String message = "";

		try {
			data = customerService.findAll();
			message = "It found " + data.size() + " customers.";

			response.setMessage(message);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);

		}
		return new ResponseEntity<>(response, status);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GeneralResponse<CustomerEntity>> getCustomerById(@PathVariable("id") Integer id) {

		GeneralResponse<CustomerEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		CustomerEntity data = null;
		String message = "";

		try {

			data = customerService.findById(id);
			message = "Customer succesfully found";

			response.setMessage(message);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.OK;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, status);

	}

	@PutMapping("/{id}")
	public ResponseEntity<GeneralResponse<CustomerEntity>> updateCustomer(@RequestBody CustomerEntity customer,
			@PathVariable("id") Integer id) {

		GeneralResponse<CustomerEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		CustomerEntity data = null;
		String message = "";

		try {
			data = customerService.findById(id);

			data.setCustomer_name(customer.getCustomer_name());
			data.setCustomer_lastname(customer.getCustomer_lastname());
			data.setCustomer_document_type(customer.getCustomer_document_type());
			data.setCustomer_document_number(customer.getCustomer_document_number());
			data.setCustomer_email(customer.getCustomer_email());
			data.setCustomer_birthday(customer.getCustomer_birthday());
			data.setCustomer_created_at(customer.getCustomer_created_at());

			customerService.save(data);
			message = "Customer succesfully updated";

			response.setMessage(message);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.CREATED;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GeneralResponse<Integer>> deleteCustomer(@PathVariable("id") Integer id) {

		GeneralResponse<Integer> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<String> customerProductStates = null;
		String message = "";
		
		try {
			customerProductStates = customerService.findCustomerProductsStateById(id);

			if (!customerProductStates.contains("A") || !customerProductStates.contains("I")) {
				customerService.deleteById(id);
				message = "Customer succesfully deleted";

				response.setMessage(message);
				response.setSuccess(true);
				response.setData(id);
				status = HttpStatus.OK;
			}

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}
}
