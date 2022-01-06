package com.bankya.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
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

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = { "product_type", "product_number", "product_state", "product_ammount" })
	private ProductModel product_id;

	// Getters and setter

	public Integer getOperation_id() {
		return operation_id;
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
