package com.example.demo.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "account-limited")
public class PublicAccount extends Account{

	private static final long serialVersionUID = 1L;

	public PublicAccount(Account acc) {
		this.setCustomer(acc.getCustomer());
		this.setExpiryDate(acc.getExpiryDate());
		this.setId(acc.getId());
	}
		
	@XmlTransient
	private Customer customer;
	
}
