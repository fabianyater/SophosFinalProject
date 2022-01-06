package com.bankya.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/v1/operations")
public class OperationRest {

	@Autowired
	private OperationDao operationdao;

	@PostMapping("/add")
	public void addOperation(@RequestBody OperationModel operation) {
		operationdao.save(operation);
	}

	@GetMapping("/all")
	public List<OperationModel> getAll() {
		return operationdao.findAll();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<OperationModel> getOperationById(@PathVariable("id") Integer id) {
		Optional<OperationModel> cm = operationdao.findById(id);
		if (cm.isPresent()) {
			return ResponseEntity.ok().body(cm.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/edit")
	public void editOperation(@RequestBody OperationModel operation) {
		operationdao.save(operation);
	}

	@DeleteMapping("/{id}")
	public void deleteOperation(@PathVariable("id") Integer id) {
		operationdao.deleteById(id);
	}

}
