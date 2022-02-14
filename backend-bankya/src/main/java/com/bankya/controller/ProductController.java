package com.bankya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.service.OperationService;
import com.bankya.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private OperationService operationService;

	@PostMapping
	public ResponseEntity<GeneralResponse<ProductEntity>> addProduct(@RequestBody ProductEntity product) {

		GeneralResponse<ProductEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity data = null;
		String message = "";

		try {
			int random = (int) (Math.random() * 10000000 + 1000000);
			product.setProduct_number(random);

			data = productService.save(product);
			response.setErrorCode(0);
			response.setMessageResult("Product succesfully created");
			message = "Successful transaction";
			response.setMessage(message);
			response.setSuccess(true);
			response.setData(data);
			status = HttpStatus.CREATED;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setErrorCode(1);
			response.setMessageResult("Product could not be created");
			response.setMessage(message);
			response.setSuccess(false);
		}

		return new ResponseEntity<>(response, status);
	}

	@PutMapping("/{id}/cancel")
	public ResponseEntity<GeneralResponse<ProductEntity>> cancelProduct(@RequestBody ProductEntity product,
			@PathVariable("id") Integer id) {

		GeneralResponse<ProductEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity data = null;
		String message = "";
		String state = "";

		try {
			data = productService.findById(id);

			if (data.getProduct_ammount() != 0) {
				response.setErrorCode(1);
				response.setMessageResult("It is not possible to cancel a product with an available balance.");
			} else {
				if (data.getProduct_state().equals("I") || data.getProduct_state().equals("A")) {
					state = "C";
					data.setProduct_state(state);
					productService.save(data);
					response.setErrorCode(3);
					response.setMessageResult("Product with id: " + data.getProduct_id() + " succesfully canceled");
				} else {
					response.setErrorCode(1);
					response.setMessageResult("It is not possible to cancel the product");
				}
			}

			message = "Successful transaction";
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

	@PutMapping("/{id}/activate")
	public ResponseEntity<GeneralResponse<ProductEntity>> activateProductState(@RequestBody ProductEntity product,
			@PathVariable("id") Integer id) {

		GeneralResponse<ProductEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity data = null;
		String message = "";
		String state = "";

		try {
			data = productService.findById(id);

			if (data == null) {
				response.setErrorCode(1);
				response.setMessageResult("Product no found");
			} else {
				if (data.getProduct_state().equals("I")) {
					state = "A";
					data.setProduct_state(state);
					productService.save(data);
					response.setErrorCode(3);
					response.setMessageResult("Product with id: " + data.getProduct_id() + " succesfully actived");
				} else {
					response.setErrorCode(1);
					response.setMessageResult("It is not possible to cancel the product");
				}
			}

			message = "Successful transaction";
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

	@PutMapping("/{id}/inactivate")
	public ResponseEntity<GeneralResponse<ProductEntity>> inactivateProductState(@RequestBody ProductEntity product,
			@PathVariable("id") Integer id) {

		GeneralResponse<ProductEntity> response = new GeneralResponse<>();
		HttpStatus status = null;
		ProductEntity data = null;
		String message = "";
		String state = "";

		try {
			data = productService.findById(id);

			if (data == null) {
				response.setErrorCode(1);
				response.setMessageResult("Product no found");
			} else {
				if (data.getProduct_state().equals("A")) {
					state = "I";
					data.setProduct_state(state);
					productService.save(data);
					response.setErrorCode(3);
					response.setMessageResult("Product with id: " + data.getProduct_id() + " succesfully actived");
				} else {
					response.setErrorCode(1);
					response.setMessageResult("It is not possible to cancel the product");
				}
			}

			message = "Successful transaction";
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

	@PostMapping("/{id}/deposit")
	public ResponseEntity<GeneralResponse<OperationEntity>> deposit(@RequestBody OperationEntity operation,
			@PathVariable("id") Integer id) {
		GeneralResponse<OperationEntity> response = new GeneralResponse<>();
		ProductEntity data = null;
		HttpStatus status = null;
		String message = "";
		double balance = 0;
		double value = 0;
		String type = "";

		try {
			data = productService.findById(id);
			if (data == null) {
				response.setErrorCode(1);
				response.setMessageResult("Product no found");
			} else {
				value = operation.getOperation_value();
				balance = data.getProduct_ammount();
				type = operation.getOperation_type();
				double add = balance + value;
				if (type.equalsIgnoreCase("deposit")) {
					operation.setOperation_balance(add);
					data.setProduct_ammount(add);
					operationService.save(operation);
					response.setErrorCode(0);
					response.setMessageResult("Deposit successfully completed");
				}
			}

			message = "Successful transaction";
			response.setMessage(message);
			response.setSuccess(true);
			response.setData(operation);
			status = HttpStatus.OK;

		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("/{id}/withdraw")
	public ResponseEntity<GeneralResponse<OperationEntity>> withdraw(@RequestBody OperationEntity operation,
			@PathVariable("id") Integer id) {
		GeneralResponse<OperationEntity> response = new GeneralResponse<>();
		String state = "";
		ProductEntity data = null;
		HttpStatus status = null;
		String message = "";
		String type = "";
		double limit = 0;
		double balance = 0;
		double value = 0;

		try {
			data = productService.findById(id);
			if (data == null) {
				response.setErrorCode(1);
				response.setMessageResult("Product no found");
			} else {
				type = productService.findProductType(id);
				if (type.equalsIgnoreCase("checking account")) {
					limit = -2000000;
				}
				value = operation.getOperation_value();
				balance = data.getProduct_ammount();
				double substract = balance - value;
				state = productService.findProductState(id);
				if (state.equals("I") || state.equals("C")) {
					response.setErrorCode(1);
					response.setMessageResult("The account must be active in order to withdraw.");
				} else if (substract < limit) {
					response.setErrorCode(1);
					response.setMessageResult("Unable to withdraw, insufficient balance.");
				} else {
					operation.setOperation_balance(substract);
					data.setProduct_ammount(substract);
					operationService.save(operation);
					response.setErrorCode(0);
					response.setMessageResult("Deposit successfully completed");
				}
			}
			System.out.println("Balance: " + balance);
			message = "Successful transaction";
			response.setMessage(message);
			response.setSuccess(true);
			response.setData(operation);
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, status);
	}

	@PostMapping("/{id}/transfer")
	public ResponseEntity<GeneralResponse<OperationEntity>> transfer(@RequestBody OperationEntity operation,
			@PathVariable("id") Integer id) {
		GeneralResponse<OperationEntity> response = new GeneralResponse<>();
		String state = "";
		ProductEntity data = null;
		HttpStatus status = null;
		String message = "";
		String type = "";
		double limit = 0;
		double balance = 0;
		double value = 0;
		int accountNumber = 0;
		int receptor = 0;
		double balanceReceptor = 0;

		try {
			data = productService.findById(id);
			if (data == null) {
				response.setErrorCode(1);
				response.setMessageResult("Product no found");
			} else {
				type = productService.findProductType(id);
				if (type.equalsIgnoreCase("checking account")) {
					limit = -2000000;
				}

				accountNumber = operation.getAccount_number();
				receptor = productService.findIdByAccountNumber(accountNumber);
				balanceReceptor = productService.findBalance(receptor);
				value = operation.getOperation_value();
				balance = productService.findBalance(id);

				OperationEntity auxOperation;
				ProductEntity productReceptor = new ProductEntity(receptor);

				double add = balanceReceptor + value;
				double substract = balance - value;

				state = productService.findProductState(id);
				if (substract < limit) {
					response.setErrorCode(1);
					response.setMessageResult("Unable to transfer, insufficient balance.");
				} else if (state.equals("I") || state.equals("C")) {
					response.setErrorCode(1);
					response.setMessageResult("The account must be active in order to transfer.");
				} else {
					auxOperation = new OperationEntity(operation.getOperation_type(), operation.getOperation_date(), value,
							operation.getOperation_description(), operation.getAccount_number(), balanceReceptor, productReceptor);;
					
					operation.setOperation_balance(substract);	
					auxOperation.setOperation_balance(add);
					productService.addAmmount(receptor, add);
					productService.substractAmmount(id, substract);
					operationService.save(operation);
					response.setErrorCode(0);
					response.setMessageResult("Transfer successfully completed");
				}
			}
			message = "Successful transaction";
			response.setMessage(message);
			response.setSuccess(true);
			response.setData(operation);
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setMessage(message);
			response.setSuccess(false);
		}
		return new ResponseEntity<>(response, status);
	}
	
	@GetMapping("/{id}/operations")
	public ResponseEntity<GeneralResponse<List<OperationEntity>>> getProductOperations(@PathVariable("id") Integer id, ProductEntity product) {

		GeneralResponse<List<OperationEntity>> response = new GeneralResponse<>();
		HttpStatus status = null;
		List<OperationEntity> data = null;
		String message = "";
		String type = "";
		
		try {
			type = productService.findProductType(id);
			
			data = productService.findProductOperations(id, type);
			
			if(data.isEmpty()) {
				response.setErrorCode(1);
				response.setMessageResult("No operations found for this product.");
			}else {
				response.setErrorCode(0);
				response.setMessageResult("Operations found successfully");
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

}
