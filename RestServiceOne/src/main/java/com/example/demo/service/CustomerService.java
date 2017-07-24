package com.example.demo.service;

import com.example.demo.domain.Customer;

public interface CustomerService {

	Customer getCustomer(Long id);

	Customer createCustomer(Customer customerDto);
		
}
