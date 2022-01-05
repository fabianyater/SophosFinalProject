package com.bankya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer client_id;

	@Column(length = 50, nullable = false)
	private String client_name;

	@Column(length = 50, nullable = false)
	private String client_lastname;

	@Column(length = 50, nullable = false)
	private String client_email;

	@Column(length = 50, nullable = false)
	private String client_document_type;

	@Column(length = 50, nullable = false)
	private String client_document_number;

	@Column(columnDefinition = "DATE")
	private String client_birthday;

	@Column(columnDefinition = "TIMESTAMP")
	private String client_created_ad;

	// Getters and setters

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_lastname() {
		return client_lastname;
	}

	public void setClient_lastname(String client_lastname) {
		this.client_lastname = client_lastname;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public String getClient_document_type() {
		return client_document_type;
	}

	public void setClient_document_type(String client_document_type) {
		this.client_document_type = client_document_type;
	}

	public String getClient_document_number() {
		return client_document_number;
	}

	public void setClient_document_number(String client_document_number) {
		this.client_document_number = client_document_number;
	}

	public String getClient_birtday() {
		return client_birthday;
	}

	public void setClient_birtday(String client_birtday) {
		this.client_birthday = client_birtday;
	}

	public String getClient_created_ad() {
		return client_created_ad;
	}

	public void setClient_created_ad(String client_created_ad) {
		this.client_created_ad = client_created_ad;
	}

}
