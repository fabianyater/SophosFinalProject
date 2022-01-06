package com.bankya.services;

import java.util.Optional;

import com.bankya.models.OperationModel;

public interface OperationService {
	public Iterable<OperationModel> findAll();

	public Optional<OperationModel> findById(int id);

	public OperationModel save(OperationModel operation);

	public void deleteById(int id);
}
