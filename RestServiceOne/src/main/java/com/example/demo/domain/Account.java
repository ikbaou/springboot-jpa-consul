package com.example.demo.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.validation.groups.ModifyAccountGroup;

public class Account {
	
	@NotNull(groups={ModifyAccountGroup.class})
	private Long id;
	@Valid
	private Customer customer;
	private Date expiryDate;

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
