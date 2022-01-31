package com.bankya.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;

	@Column(length = 50, nullable = false)
	private String product_type;

	@Column(length = 50, nullable = false)
	private Integer product_number;

	@Column(length = 50, nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'A'")
	private String product_state;

	@Column(length = 10, nullable = false, columnDefinition = "NUMERIC DEFAULT 0")
	private Double product_ammount;

	@Column(nullable = false)
	private String product_created_at;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "customer_name", "customer_lastname", "customer_email", "customer_birthday",
			"customer_created_at", "customer_document_type", "customer_document_number" })
	private CustomerEntity customer_id;

	public ProductEntity() {

	}

	public ProductEntity(Integer product_id) {
		this.product_id = product_id;
	}

	public ProductEntity(Integer product_id, String product_type, Integer product_number, String product_state,
			Double product_ammount, String product_created_at, CustomerEntity customer_id) {
		super();
		this.product_id = product_id;
		this.product_type = product_type;
		this.product_number = product_number;
		this.product_state = product_state;
		this.product_ammount = product_ammount;
		this.product_created_at = product_created_at;
		this.customer_id = customer_id;
	}

	// Getters and setters

	public Integer getProduct_id() {
		return product_id;
	}

	public String getProduct_created_at() {
		return product_created_at;
	}

	public void setProduct_created_at(String product_created_at) {
		this.product_created_at = product_created_at;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public Integer getProduct_number() {
		return product_number;
	}

	public void setProduct_number(Integer product_number) {
		this.product_number = product_number;
	}

	public String getProduct_state() {
		return product_state;
	}

	public void setProduct_state(String product_state) {
		this.product_state = product_state;
	}

	public Double getProduct_ammount() {
		return product_ammount;
	}

	public void setProduct_ammount(Double product_ammount) {
		this.product_ammount = product_ammount;
	}

	public CustomerEntity getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(CustomerEntity customer_id) {
		this.customer_id = customer_id;
	}

}
