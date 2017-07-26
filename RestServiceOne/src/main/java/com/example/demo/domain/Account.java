package com.example.demo.domain;

import java.util.Date;

import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.domain.validation.groups.ModifyAccountGroup;
import com.example.demo.domain.views.Views;
import com.example.demo.util.AbstractDomain;
import com.fasterxml.jackson.annotation.JsonView;

@Default
@Named
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "base/account")
public class Account extends AbstractDomain {
	
	private static final long serialVersionUID = 1L;

	@NotNull(groups={ModifyAccountGroup.class})	
	@JsonView(Views.Sensitive.class)
	protected Long id;
	
	@Valid
	@JsonView(Views.Internal.class)
	protected Customer customer;
		
	@JsonView(Views.Public.class)
	protected Date expiryDate;
	
	@NotNull(groups={ModifyAccountGroup.class})
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
