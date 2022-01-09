package com.bankya.rest;

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

import com.bankya.dao.OperationDao;
import com.bankya.models.OperationModel;
import com.bankya.models.ProductModel;
import com.bankya.services.OperationService;
import com.bankya.services.ProductService;

import exceptions.HandleException;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/v1/operations")
public class OperationRest {

	@Autowired
	private OperationService operationService;

	@PostMapping
	public ResponseEntity<OperationModel> addOperation(@RequestBody OperationModel operation) throws HandleException {
		int id = operation.getProduct_id().getProduct_id();
		int limite = 0;
		double balance = operationService.findBalance(id);
		double value = operation.getOperation_value();
		String type = operationService.findProductType(id);
		String state = operationService.findProductState(id);

		if (type.equalsIgnoreCase("cuenta corriente")) {
			limite = -2000000;
		}

		if (operation.getOperation_type().equalsIgnoreCase("retiro")) {
			double substract = Math.abs(balance) - value;
			if (substract < limite) {
				throw new HandleException("No puede retirar. Saldo insuficiente");
			}
			if (state.equalsIgnoreCase("I") || state.equalsIgnoreCase("C")) {
				throw new HandleException("Debe tener una cuenta activa para retirar.");
			}
			operationService.substractAmmount(id, substract);
		}

		if (operation.getOperation_type().equalsIgnoreCase("consignacion")) {

			if (state.equalsIgnoreCase("C")) {
				throw new HandleException("Debe tener una cuenta activa para retirar.");
			}

			double add = balance + value;
			operationService.addAmmount(id, add);
		}

		if (operation.getOperation_type().equalsIgnoreCase("transaccion")) {

			try {
				int ammount = operation.getAccount_number();
				int receptor = operationService.findIdByAccountNumber(ammount);
				double balanceRepector = operationService.findBalance(receptor);

				double add = balanceRepector + value;
				double substract = balance - value;

				if (substract < limite) {
					throw new HandleException("No puede realizar la transacción, excedió el limite de sobregiro");
				}

				operationService.addAmmount(receptor, add);
				operationService.substractAmmount(id, substract);
			} catch (Exception he) {
				throw new HandleException("Error");
			}

		}

		return ResponseEntity.status(HttpStatus.CREATED).body(operationService.save(operation));
	}

	@GetMapping
	public List<OperationModel> getAll() {
		List<OperationModel> lOperation = StreamSupport.stream(operationService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return lOperation;
	}

	/*
	 * @GetMapping("/{id}") public ResponseEntity<OperationModel>
	 * getOperationById(@PathVariable("id") Integer id) { Optional<OperationModel>
	 * oOperation = operationService.findById(id); if (!oOperation.isPresent()) {
	 * return ResponseEntity.notFound().build(); }
	 * 
	 * return ResponseEntity.ok().body(oOperation.get());
	 * 
	 * }
	 * 
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
