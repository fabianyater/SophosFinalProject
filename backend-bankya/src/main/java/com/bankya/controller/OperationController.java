package com.bankya.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.bankya.entity.OperationEntity;
import com.bankya.entity.ProductEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.repository.OperationRepository;
import com.bankya.service.OperationService;
import com.bankya.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
@RestController
@RequestMapping("api/v1/operations")
public class OperationController {

	@Autowired
	private OperationService operationService;

	@PostMapping
	public ResponseEntity<OperationEntity> addOperation(@RequestBody OperationEntity operation) throws GeneralResponse {
		int id = operation.getProduct_id().getProduct_id();
		int limit = 0;
		double balance = operationService.findBalance(id);
		double value = operation.getOperation_value();
		String type = operationService.findProductType(id);
		String state = operationService.findProductState(id);

		if (type.equalsIgnoreCase("checking account")) {
			limit = -2000000;
		}

		if (operation.getOperation_type().equalsIgnoreCase("withdraw")) {
			double substract = balance - value;
			if (substract < limit) {
				return ResponseEntity.badRequest().build();
			}
			if (state.equalsIgnoreCase("I") || state.equalsIgnoreCase("C")) {
				throw new GeneralResponse("Debe tener una cuenta activa para retirar.");
			}
			operation.setOperation_balance(balance - value);
			operationService.substractAmmount(id, substract);
		}

		if (operation.getOperation_type().equalsIgnoreCase("deposit")) {

			if (state.equalsIgnoreCase("C")) {
				throw new GeneralResponse("Debe tener una cuenta activa para retirar.");
			}

			operation.setOperation_balance(balance + value);
			double add = balance + value;
			operationService.addAmmount(id, add);
		}

		if (operation.getOperation_type().equalsIgnoreCase("transfer")) {

			try {
				int account_number = operation.getAccount_number();
				int receptor = operationService.findIdByAccountNumber(account_number);
				double balanceRepector = operationService.findBalance(receptor);
				OperationEntity auxOperation;
				ProductEntity product = new ProductEntity(receptor);

				double add = balanceRepector + value;
				double substract = balance - value;

				if (substract < limit) {
					throw new GeneralResponse("No puede realizar la transacción, excedió el limite de sobregiro");
				}

				if (state.equalsIgnoreCase("I") || state.equalsIgnoreCase("C")) {
					throw new GeneralResponse("Debe tener una cuenta activa para retirar.");
				}


				auxOperation = new OperationEntity(operation.getOperation_type(), operation.getOperation_date(), value,
						operation.getOperation_description(), operation.getAccount_number(), balanceRepector, product);

				operation.setOperation_balance(balance - value);
				auxOperation.setOperation_balance(balanceRepector + value);
				operationService.addAmmount(receptor, add);
				operationService.substractAmmount(id, substract);
				operationService.save(auxOperation);

			} catch (Exception he) {
				throw new GeneralResponse("Error");
			}

		}

		return ResponseEntity.status(HttpStatus.CREATED).body(operationService.save(operation));
	}

	@GetMapping
	public List<OperationEntity> getAll() {
		List<OperationEntity> lOperation = StreamSupport.stream(operationService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return lOperation;
	}

	@GetMapping("/product/{id}")
	public Iterable<OperationEntity> getProductOperations(@PathVariable("id") Integer id, ProductEntity product) {

		String productType = operationService.findProductType(id);

		Iterable<OperationEntity> oOperation = operationService.findProductOperations(id, productType);

		return oOperation;
	}

	@GetMapping("/{id}")
	public ResponseEntity<OperationEntity> getOperationById(@PathVariable("id") Integer id) {
		Optional<OperationEntity> oOperation = operationService.findById(id);
		if (!oOperation.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(oOperation.get());

	}

	/*
	 * @PutMapping("/{id}") public ResponseEntity<OperationModel>
	 * editOperation(@RequestBody OperationModel operation,
	 * 
	 * @PathVariable("id") Integer id) { Optional<OperationModel> oOperation =
	 * operationService.findById(id);
	 * 
	 * if (!oOperation.isPresent()) { return ResponseEntity.notFound().build(); }
	 * 
	 * oOperation.get().setOperation_date(operation.getOperation_date());
	 * oOperation.get().setOperation_description(operation.getOperation_description(
	 * )); oOperation.get().setOperation_type(operation.getOperation_type());
	 * oOperation.get().setOperation_value(operation.getOperation_value());
	 * 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(operationService.save(
	 * oOperation.get())); }
	 * 
	 * @DeleteMapping("/{id}") public ResponseEntity<OperationModel>
	 * deleteClient(@PathVariable("id") Integer id) { if
	 * (!operationService.findById(id).isPresent()) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * operationService.deleteById(id); return ResponseEntity.ok().build(); }
	 */

}
