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

import com.bankya.dao.ClientDao;
import com.bankya.model.ClientModel;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/v1/clients")
public class ClientRest {

	@Autowired
	private ClientDao clientdao;

	@PostMapping("/add")
	public void addClient(@RequestBody ClientModel client) {
		clientdao.save(client);
	}

	@GetMapping("/all")
	public List<ClientModel> getAll() {
		return clientdao.findAll();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ClientModel> getClientById(@PathVariable("id") Integer id) {
		Optional<ClientModel> cm = clientdao.findById(id);
		if (cm.isPresent()) {
			return ResponseEntity.ok().body(cm.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/edit")
	public void editClient(@RequestBody ClientModel client) {
		clientdao.save(client);
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") Integer id) {
		clientdao.deleteById(id);
	}
}
