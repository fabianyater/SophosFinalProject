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

@Entity(name = "product")
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;

	@Column(length = 50, nullable = false)
	private String product_type;

	@Column(length = 50, nullable = false)
	private Integer product_number;

	@Column(length = 50, nullable = false, columnDefinition = "ENUM('A','I','C') DEFAULT 'A'")
	private String product_state;

	@Column(length = 10, nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
	private Double product_ammount;

	@Column(nullable = false)
	private String product_created_at;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "client_name", "client_lastname", "client_email", "client_birthday",
			"client_created_at", "client_document_type", "client_document_number" })
	private ClientModel client_id;

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

	public ClientModel getClient_id() {
		return client_id;
	}

	public void setClient_id(ClientModel client_id) {
		this.client_id = client_id;
	}

}
