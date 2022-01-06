package com.bankya.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankya.dao.ClientDao;
import com.bankya.models.ClientModel;

@Service
public class ClientServiceImplementation implements ClientService {

	@Autowired
	ClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public Iterable<ClientModel> findAll() {
		return clientDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ClientModel> findById(int id) {
		return clientDao.findById(id);
	}

	@Override
	@Transactional
	public ClientModel save(ClientModel client) {
		return clientDao.save(client);
	}

	@Override
	public void deleteById(int id) {
		clientDao.deleteById(id);
	}

}
