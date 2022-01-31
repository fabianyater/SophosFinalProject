package com.bankya.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_id;

	@Column(length = 50, nullable = false)
	private String customer_name;

	@Column(length = 50, nullable = false)
	private String customer_lastname;

	@Column(length = 50, nullable = false)
	private String customer_email;

	@Column(length = 50, nullable = false)
	private String customer_document_type;

	@Column(length = 50, nullable = false)
	private String customer_document_number;

	@Column(columnDefinition = "DATE", nullable = false)
	private Date customer_birthday;

	@Column(columnDefinition = "TIMESTAMP", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp customer_created_at;
		
	public CustomerEntity() {
	}

	public CustomerEntity(Integer customer_id, String customer_name, String customer_lastname, String customer_email,
			String customer_document_type, String customer_document_number, Date customer_birthday,
			Timestamp customer_created_at) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_lastname = customer_lastname;
		this.customer_email = customer_email;
		this.customer_document_type = customer_document_type;
		this.customer_document_number = customer_document_number;
		this.customer_birthday = customer_birthday;
		this.customer_created_at = customer_created_at;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_lastname() {
		return customer_lastname;
	}

	public void setCustomer_lastname(String customer_lastname) {
		this.customer_lastname = customer_lastname;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_document_type() {
		return customer_document_type;
	}

	public void setCustomer_document_type(String customer_document_type) {
		this.customer_document_type = customer_document_type;
	}

	public String getCustomer_document_number() {
		return customer_document_number;
	}

	public void setCustomer_document_number(String customer_document_number) {
		this.customer_document_number = customer_document_number;
	}

	public Date getCustomer_birthday() {
		return customer_birthday;
	}

	public void setCustomer_birthday(Date customer_birthday) {
		this.customer_birthday = customer_birthday;
	}

	public Timestamp getCustomer_created_at() {
		return customer_created_at;
	}

	public void setCustomer_created_at(Timestamp customer_created_at) {
		this.customer_created_at = customer_created_at;
	}
	
	
}
