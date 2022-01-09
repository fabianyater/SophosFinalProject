package com.bankya.services;

import java.util.List;
import java.util.Optional;

import com.bankya.models.ClientModel;
import com.bankya.models.ProductModel;

public interface ClientService {

	public Iterable<ClientModel> findAll();

	public Optional<ClientModel> findById(int id);

	public ClientModel save(ClientModel client);

	public void deleteById(int id);
	
	public List<String> findClientProductsState(Integer id);
}
