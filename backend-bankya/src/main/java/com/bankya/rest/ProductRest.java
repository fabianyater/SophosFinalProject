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

import com.bankya.dao.ProductDao;
import com.bankya.model.ClientModel;
import com.bankya.model.ProductModel;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/v1/products")
public class ProductRest {

	@Autowired
	private ProductDao productdao;

	@PostMapping("/add")
	public void addProduct(@RequestBody ProductModel product) {
		System.out.println("Clientttteee:" + product.getClient_id().getClient_id().intValue());
		productdao.save(product);
	}

	@GetMapping("/all")
	public List<ProductModel> getAll() {
		return productdao.findAll();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ProductModel> getClientById(@PathVariable("id") Integer id) {
		Optional<ProductModel> cm = productdao.findById(id);
		if (cm.isPresent()) {
			return ResponseEntity.ok().body(cm.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/edit")
	public void editClient(@RequestBody ProductModel product) {
		productdao.save(product);
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") Integer id) {
		productdao.deleteById(id);
	}

}
