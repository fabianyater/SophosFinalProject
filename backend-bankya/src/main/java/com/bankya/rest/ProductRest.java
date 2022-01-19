package com.bankya.rest;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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

import exceptions.HandleException;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("api/v1/products")
public class ProductRest {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel product) throws HandleException {
		int random = (int) (Math.random() * 10000000 + 1000000);
		product.setProduct_number(random);

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@GetMapping
	public List<ProductModel> getAll() {
		List<ProductModel> lProduct = StreamSupport.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return lProduct;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProuctById(@PathVariable("id") Integer id) throws HandleException {
		Optional<ProductModel> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
			throw new HandleException("Producto no encontrado");
		}

		return ResponseEntity.ok().body(oProduct.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> editProduct(@RequestBody ProductModel product, @PathVariable("id") Integer id)
			throws HandleException {
		Optional<ProductModel> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
			throw new HandleException("Producto no encontrado. No se puede modificar");
		}

		oProduct.get().setProduct_type(product.getProduct_type());
		oProduct.get().setProduct_number(product.getProduct_number());
		oProduct.get().setProduct_ammount(product.getProduct_ammount());
		oProduct.get().setProduct_state(product.getProduct_state());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable("id") Integer id) throws HandleException {

		if (!productService.findById(id).isPresent()) {
			throw new HandleException("Producto no encontrado. No se puede eliminar");
		}

		productService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/client/{id}")
	public Iterable<ProductModel> getClientProducts(@PathVariable("id") Integer id) {

		Iterable<ProductModel> oProduct = productService.findClientProducts(id);

		return oProduct;
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<ProductModel> cancelProduct(@RequestBody ProductModel product, @PathVariable("id") Integer id)
			throws HandleException {
		
		String state = "";
		Optional<ProductModel> oProduct = productService.findById(id);
		String currentState = product.getProduct_state();
		if (!oProduct.isPresent()) {
			throw new HandleException("Producto no encontrado. No se puede cancelar");
		}

		System.out.println(oProduct.get().getProduct_ammount());

		if (oProduct.get().getProduct_ammount() > 0) {
			throw new HandleException(
					"No puede cancelar un producto con saldo mayor a cero (0). Debe retirar el dinero primero");
		}
		
		if (currentState.equals("A") || currentState.equals("I")) {
			state = "C";
		}

		oProduct.get().setProduct_state(state);

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}

	@PutMapping("/update-state/{id}")
	public ResponseEntity<ProductModel> updateProductState(@RequestBody ProductModel product,
			@PathVariable("id") Integer id) {

		String state = "";

		Optional<ProductModel> oProduct = productService.findById(id);

		String currentState = product.getProduct_state();

		if (currentState.equals("A")) {
			state = "I";
		} else {
			state = "A";
		}

		oProduct.get().setProduct_state(state);

		System.out.println("Estado: " + oProduct.get().getProduct_state().toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}
}
