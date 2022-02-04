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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankya.entity.CustomerEntity;
import com.bankya.entity.ProductEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
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
		List<CustomerEntity> data;
		String message = "";

		try {
			data = customerService.findAll();

			if (data.isEmpty()) {
				response.setErrorCode(1);
				response.setMessageResult("No registered customers");
			} else {
				response.setErrorCode(0);
				response.setMessageResult("It found " + data.size() + " customers.");
			}

			message = "Successful transaction.";
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

			if (data == null || data.getCustomer_id() == null) {
				response.setErrorCode(1);
				response.setMessageResult("Customer not found");
			} else {
				response.setErrorCode(0);
				response.setMessageResult("Customer with id: " + data.getCustomer_id() + " succesfully found");
			}

			message = "Succesful transaction";
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

	@GetMapping("/{customerId}/products")
	public ResponseEntity<GeneralResponse<List<ProductEntity>>> getCustomerProducts(
			@PathVariable("customerId") Integer customerId) {

		GeneralResponse<List<ProductEntity>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<ProductEntity> data = null;
		CustomerEntity customer = null;
		String message = "";

		try {
			customer = customerService.findById(customerId);

			if (customer == null || customer.getCustomer_id() == null) {
				response.setErrorCode(1);
				response.setMessageResult("Customer not found");
			} else {
				data = customerService.findCustomerProducts(customerId);
				if (data.isEmpty()) {
					response.setErrorCode(1);
					response.setMessageResult("No registered products for customer: " + customerId);
				} else {
					response.setErrorCode(0);
					response.setMessageResult("It found " + data.size() + " products from customer: " + customerId);
				}
			}

			message = "Successful transaction";
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

	@GetMapping("/{customerId}/products/{productId}")
	public ResponseEntity<GeneralResponse<ProductEntity>> getCustomerProductById(
			@PathVariable("customerId") Integer customerId, @PathVariable("productId") Integer productId) {

		GeneralResponse<ProductEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity data = null;
		CustomerEntity customer = null;
		String message = "";

		try {
			customer = customerService.findById(customerId);

			if (customer == null || customer.getCustomer_id() == null) {
				response.setErrorCode(1);
				response.setMessageResult("Customer not found");
			} else {
				data = customerService.findCustomerProductById(customerId, productId);
				if (data == null || data.getProduct_id() == null) {
					response.setErrorCode(1);
					response.setMessageResult("Product not found");
				} else {
					response.setErrorCode(0);
					response.setMessageResult("Product with id: " + productId + " succesfully found");
				}
			}

			message = "Successful transaction";
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

		try {
			customerProductStates = customerService.findCustomerProductsStateById(id);

			if (customerProductStates.contains("A") || customerProductStates.contains("I")) {
				response.setErrorCode(1);
				response.setMessageResult("Can't delete a customer with active or inactive products");
			} else {
				customerService.deleteById(id);
				response.setErrorCode(0);
				response.setMessageResult("Customer succesfully deleted");
			}

			response.setMessage("Successful transaction");
			response.setSuccess(true);
			response.setData(id);
			status = HttpStatus.OK;

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage("Something has failed. Please contact suuport.");
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}
}
