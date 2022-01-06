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

import com.bankya.models.ProductModel;
import com.bankya.services.ProductService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("api/v1/products")
public class ProductRest {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@GetMapping
	public List<ProductModel> getAll() {
		List<ProductModel> lProduct = StreamSupport.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return lProduct;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getClientById(@PathVariable("id") Integer id) {
		Optional<ProductModel> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(oProduct.get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> editClient(@RequestBody ProductModel product, @PathVariable("id") Integer id) {
		Optional<ProductModel> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		oProduct.get().setProduct_type(product.getProduct_type());
		oProduct.get().setProduct_number(product.getProduct_number());
		oProduct.get().setProduct_ammount(product.getProduct_ammount());
		oProduct.get().setProduct_state(product.getProduct_state());

		System.out.println("oProduct: " + oProduct);
		System.out.println("oProduct: " + oProduct.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteClient(@PathVariable("id") Integer id) {
		if (!productService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
