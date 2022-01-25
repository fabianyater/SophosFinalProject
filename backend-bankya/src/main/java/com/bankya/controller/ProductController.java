package com.bankya.controller;

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

import com.bankya.entity.ProductEntity;
import com.bankya.model.GeneralResponse;
import com.bankya.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product){
		int random = (int) (Math.random() * 10000000 + 1000000);
		product.setProduct_number(random);

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@GetMapping
	public List<ProductEntity> getAll() {
		List<ProductEntity> lProduct = StreamSupport.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return lProduct;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductEntity> getProuctById(@PathVariable("id") Integer id) {
		Optional<ProductEntity> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
		}

		return ResponseEntity.ok().body(oProduct.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductEntity> editProduct(@RequestBody ProductEntity product, @PathVariable("id") Integer id)
			throws GeneralResponse {
		Optional<ProductEntity> oProduct = productService.findById(id);
		if (!oProduct.isPresent()) {
			throw new GeneralResponse("Producto no encontrado. No se puede modificar");
		}

		oProduct.get().setProduct_type(product.getProduct_type());
		oProduct.get().setProduct_number(product.getProduct_number());
		oProduct.get().setProduct_ammount(product.getProduct_ammount());
		oProduct.get().setProduct_state(product.getProduct_state());

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductEntity> deleteProduct(@PathVariable("id") Integer id) throws GeneralResponse {

		if (!productService.findById(id).isPresent()) {
			throw new GeneralResponse("Producto no encontrado. No se puede eliminar");
		}

		productService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/client/{id}")
	public Iterable<ProductEntity> getClientProducts(@PathVariable("id") Integer id) {

		Iterable<ProductEntity> oProduct = productService.findClientProducts(id);

		return oProduct;
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<ProductEntity> cancelProduct(@RequestBody ProductEntity product, @PathVariable("id") Integer id)
			throws GeneralResponse {
		
		String state = "";
		Optional<ProductEntity> oProduct = productService.findById(id);
		String currentState = product.getProduct_state();
		if (!oProduct.isPresent()) {
			throw new GeneralResponse("Producto no encontrado. No se puede cancelar");
		}

		System.out.println(oProduct.get().getProduct_ammount());

		if (oProduct.get().getProduct_ammount() > 0) {
			throw new GeneralResponse(
					"No puede cancelar un producto con saldo mayor a cero (0). Debe retirar el dinero primero");
		}
		
		if (currentState.equals("A") || currentState.equals("I")) {
			state = "C";
		}

		oProduct.get().setProduct_state(state);

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}

	@PutMapping("/update-state/{id}")
	public ResponseEntity<ProductEntity> updateProductState(@RequestBody ProductEntity product,
			@PathVariable("id") Integer id) {

		String state = "";

		Optional<ProductEntity> oProduct = productService.findById(id);

		String currentState = product.getProduct_state();

		if (currentState.equals("A")) {
			state = "I";
		} else {
			state = "A";
		}

		oProduct.get().setProduct_state(state);

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(oProduct.get()));
	}
}
