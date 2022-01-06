package com.bankya.services;

import java.util.Optional;

import com.bankya.models.ClientModel;

public interface ClientService {

	public Iterable<ClientModel> findAll();

	public Optional<ClientModel> findById(int id);

	public ClientModel save(ClientModel client);

	public void deleteById(int id);
}
