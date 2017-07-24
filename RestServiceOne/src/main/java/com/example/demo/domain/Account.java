package com.example.demo.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.domain.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "account")
public class Account {
	
	@JsonView(Views.Sensitive.class)
	protected Long id;
	
	@JsonView(Views.Internal.class)
	protected Customer customer;
		
	@JsonView(Views.Public.class)
	protected Date expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
