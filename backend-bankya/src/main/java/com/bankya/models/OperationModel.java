package com.bankya.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "operation")
public class OperationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer operation_id;

	@Column(length = 50)
	private String operation_type;

	@Column(length = 50, columnDefinition = "DATE")
	private String operation_date;

	@Column(length = 10)
	private Double operation_value;

	@Column(length = 150)
	private String operation_description;

	@Column(length = 50)
	private Integer account_number;

	@Column(length = 50)
	private Double operation_balance;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "product_type", "product_created_at", "client_id", "product_number",
			"product_state" })
	private ProductModel product_id;

	public OperationModel() {

	}

	public OperationModel(String operation_type, String operation_date, Double operation_value,
			String operation_description, Integer account_number, Double operation_balance, ProductModel product_id) {
		this.operation_type = operation_type;
		this.operation_date = operation_date;
		this.operation_value = operation_value;
		this.operation_description = operation_description;
		this.account_number = account_number;
		this.operation_balance = operation_balance;
		this.product_id = product_id;
	}

	// Getters and setter

	public Integer getOperation_id() {
		return operation_id;
	}

	public Double getOperation_balance() {
		return operation_balance;
	}

	public void setOperation_balance(Double operation_balance) {
		this.operation_balance = operation_balance;
	}

	public Integer getAccount_number() {
		return account_number;
	}

	public void setAccount_number(Integer account_number) {
		this.account_number = account_number;
	}

	public ProductModel getProduct_id() {
		return product_id;
	}

	public void setProduct_id(ProductModel product_id) {
		this.product_id = product_id;
	}

	public void setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public String getOperation_date() {
		return operation_date;
	}

	public void setOperation_date(String operation_date) {
		this.operation_date = operation_date;
	}

	public Double getOperation_value() {
		return operation_value;
	}

	public void setOperation_value(Double operation_value) {
		this.operation_value = operation_value;
	}

	public String getOperation_description() {
		return operation_description;
	}

	public void setOperation_description(String operation_description) {
		this.operation_description = operation_description;
	}

}
