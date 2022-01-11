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

import com.bankya.models.ClientModel;
import com.bankya.models.ProductModel;
import com.bankya.services.ClientService;

import exceptions.HandleException;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RestController
@RequestMapping("api/v1/clients")
public class ClientRest {

	@Autowired
	private ClientService clientService;

	@PostMapping
	public ResponseEntity<?> addClient(@RequestBody ClientModel client) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
	}

	@GetMapping
	public Iterable<ClientModel> getAllClients() {

		/*
		 * List<ClientModel> lClient =
		 * StreamSupport.stream(clientService.findAll().spliterator(), false)
		 * .collect(Collectors.toList());
		 */
		return clientService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientModel> getClientById(@PathVariable("id") Integer id) throws HandleException {
		Optional<ClientModel> oClient = clientService.findById(id);
		if (!oClient.isPresent()) {
			throw new HandleException("Cliente no encontrado");
		}
		return ResponseEntity.ok().body(oClient.get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editClient(@RequestBody ClientModel client, @PathVariable("id") Integer id) throws HandleException {
		Optional<ClientModel> oClient = clientService.findById(id);
		if (!oClient.isPresent()) {
			throw new HandleException("Cliente no encontrado, no se puede modificar");
		}

		// BeanUtils.copyProperties(client, oClient.get()); Copia todo el objeto

		oClient.get().setClient_name(client.getClient_name());
		oClient.get().setClient_lastname(client.getClient_lastname());
		oClient.get().setClient_document_type(client.getClient_document_type());
		oClient.get().setClient_document_number(client.getClient_document_number());
		oClient.get().setClient_email(client.getClient_email());
		oClient.get().setClient_birthday(client.getClient_birthday());
		oClient.get().setClient_created_at(client.getClient_created_at());

		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(oClient.get()));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id) throws HandleException {

		List<String> oState = clientService.findClientProductsState(id);

		if (!clientService.findById(id).isPresent()) {
			throw new HandleException("Cliente no encontrado");
		}

		if (oState.contains("A") || oState.contains("I")) {
			throw new HandleException("No se puede eliminar un cliente con productos vigentes");
			//return ResponseEntity.badRequest().build();
		}

		clientService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
